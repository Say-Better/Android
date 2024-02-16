package gdsc.solutionchallenge.saybetter.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.theme.white
import gdsc.solutionchallenge.saybetter.presentation.ui.login.LoginActivity
import gdsc.solutionchallenge.saybetter.presentation.ui.login.LoginViewModel
import gdsc.solutionchallenge.saybetter.presentation.ui.main.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    // Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 시도
        viewModel.tryLogin(this)

        // 로그인 결과를 별도의 코루틴에서 수집
        lifecycleScope.launchWhenCreated {
            viewModel.loginResult.collect { isLogin ->
                if (isLogin) {
                    if (auth.currentUser != null) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                } else {
                    // 로그인 안되어있을 때 로그인 페이지 열림
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
                finish() // 스플래시 액티비티 종료
            }
        }

        // 3초 동안 스플래시 화면 유지
        lifecycleScope.launchWhenCreated {
            delay(3000)
        }

        setContent {
            Surface(color = white, modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = null,
                    modifier = Modifier.width(301.dp)
                        .height(141.dp)
                )
            }
        }
    }
}



@Preview
@Composable
fun Splash(){

        Surface(color = white, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = null,
                modifier = Modifier.width(301.dp)
                    .height(141.dp)
            )
        }

}
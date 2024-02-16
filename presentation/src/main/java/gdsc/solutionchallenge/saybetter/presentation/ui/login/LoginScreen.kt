import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.theme.white


@Composable
fun LoginScreen(content: () -> Unit){
    Surface(color = white, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Say better life,",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 18.dp, top = 96.dp)
            )
            Text(
                text = "Say better dream",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 18.dp)
            )
            Text(
                text = "로그인하여 Say Better와 함께하세요.",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 18.dp , top = 3.dp)
            )
            Spacer(modifier = Modifier.height(165.dp))
            Image(
                painter = painterResource(id = R.drawable.login_img),
                contentDescription = null,
                modifier = Modifier
                    .width(225.06.dp)
                    .height(263.dp)
                    .padding(start = 36.dp)
            )
            Spacer(modifier = Modifier.height(74.dp))
            SignInGoogleButton {
                content()
            }
        }
    }

}

@Composable
fun SignInGoogleButton(onClick: () -> Unit){
    Image(
        painter = painterResource(id = R.drawable.google_img),
        contentDescription = null,
        modifier = Modifier
            .width(450.dp)
            .height(67.04.dp)
            .clickable(onClick = onClick) // 클릭 이벤트에 함수 연결
    )
}

@Preview
@Composable
fun LoginPreview(){
    Surface(color = white, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Say better life,",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 18.dp, top = 96.dp)
            )
            Text(
                text = "Say better dream",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 18.dp)
            )
            Text(
                text = "로그인하여 Say Better와 함께하세요.",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 18.dp , top = 3.dp)
            )
            Spacer(modifier = Modifier.height(165.dp))
            Image(
                painter = painterResource(id = R.drawable.login_img),
                contentDescription = null,
                modifier = Modifier
                    .width(225.06.dp)
                    .height(263.dp)
                    .padding(start = 36.dp)
            )
            Spacer(modifier = Modifier.height(74.dp))
            Image(
                painter = painterResource(id = R.drawable.google_img),
                contentDescription = null,
                modifier = Modifier
                    .width(450.dp)
                    .height(67.04.dp)
            )
        }
    }
}

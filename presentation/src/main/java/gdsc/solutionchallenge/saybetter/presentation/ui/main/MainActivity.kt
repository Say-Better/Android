package gdsc.solutionchallenge.saybetter.presentation.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gdsc.solutionchallenge.saybetter.presentation.component.BottomNav
import gdsc.solutionchallenge.saybetter.presentation.theme.SayBetterTheme
import gdsc.solutionchallenge.saybetter.presentation.ui.home.HomeScreen
import gdsc.solutionchallenge.saybetter.presentation.ui.leaners.LearnersScreen
import gdsc.solutionchallenge.saybetter.presentation.ui.my.MyScreen
import gdsc.solutionchallenge.saybetter.presentation.ui.solution.SolutionsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SayBetterTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    LaunchedEffect(currentRoute) {
        Log.d("Navigation", "Current route: $currentRoute")
    }
    Scaffold(
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { Box(modifier = Modifier.padding(innerPadding)) { HomeScreen() } }
            composable("learners") { Box(modifier = Modifier.padding(innerPadding)) { LearnersScreen() } }
            composable("solutions") { Box(modifier = Modifier.padding(innerPadding)) { SolutionsScreen() } }
            composable("my") { Box(modifier = Modifier.padding(innerPadding)) { MyScreen() } }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SayBetterTheme {
        Navigation()

    }
}
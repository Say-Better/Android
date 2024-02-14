package gdsc.solutionchallenge.saybetter.presentation.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.model.BottomNavItem
import gdsc.solutionchallenge.saybetter.presentation.theme.bg3
import gdsc.solutionchallenge.saybetter.presentation.theme.more_green

@Composable
fun BottomNav(navController: NavController) {

    val bottomNavItems = listOf(
        BottomNavItem(
            route = "home",
            icon = R.drawable.ic_home_gray,
            selectedIcon = R.drawable.ic_home_green,
            label = "Home",
            selectedLabelColor = more_green,
            unselectedLabelColor = bg3
        ),
        BottomNavItem(
            route = "learners",
            icon = R.drawable.ic_learners_gray,
            selectedIcon = R.drawable.ic_learners_green,
            label = "Leaners",
            selectedLabelColor = more_green,
            unselectedLabelColor = bg3
        ),
        BottomNavItem(
            route = "solutions",
            icon = R.drawable.ic_solutions_gray,
            selectedIcon = R.drawable.ic_solutions_green,
            label = "Solutions",
            selectedLabelColor = more_green,
            unselectedLabelColor = bg3
        ),
        BottomNavItem(
            route = "my",
            icon = R.drawable.ic_my_gray,
            selectedIcon = R.drawable.ic_my_green,
            label = "My",
            selectedLabelColor = more_green,
            unselectedLabelColor = bg3
        )
    )

    BottomNavigation {
        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (navController.currentDestination?.route == item.route) {
                        Icon(painterResource(item.selectedIcon), contentDescription = null, tint = more_green)
                    } else {
                        Icon(painterResource(item.icon), contentDescription = null, tint = bg3)
                    }
                },
                label = { Text(item.label, color = if (navController.currentDestination?.route == item.route) item.selectedLabelColor else item.unselectedLabelColor) },
                selected = navController.currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}
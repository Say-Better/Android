package gdsc.solutionchallenge.saybetter.presentation.model

import androidx.compose.ui.graphics.Color

data class BottomNavItem(
    val route: String,
    val icon: Int,
    val selectedIcon: Int,
    val label: String,
    val selectedLabelColor: Color,
    val unselectedLabelColor: Color
)

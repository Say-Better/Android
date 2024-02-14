package gdsc.solutionchallenge.saybetter.presentation.ui.solution

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.presentation.theme.more_green

@Composable
fun SolutionsScreen() {
    Box(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()){
        Box {
            contentColorFor(backgroundColor = more_green)
        }
    }
}
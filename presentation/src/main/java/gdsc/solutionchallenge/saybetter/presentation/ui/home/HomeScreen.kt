package gdsc.solutionchallenge.saybetter.presentation.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.model.LearnerProfile
import gdsc.solutionchallenge.saybetter.presentation.model.Solution
import gdsc.solutionchallenge.saybetter.presentation.theme.bg3
import gdsc.solutionchallenge.saybetter.presentation.theme.white

val learnerProfiles = listOf(
    LearnerProfile(
        name = "John Doe",
        photoUrl = R.drawable.learner_img,
        age = 25,
        gender = "남"
    ),
    LearnerProfile(
        name = "Jane Doe",
        photoUrl = R.drawable.learner_img,
        age = 30,
        gender = "여"
    )
)
val solutions = listOf(
    Solution(
        solutionImage = R.drawable.solution_img,
        solutionTitle = "솔루션 제목 1",
        stage = 4
    ),
    Solution(
        solutionImage = R.drawable.solution_img,
        solutionTitle = "솔루션 제목 2",
        stage = 4
    )
)

@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Say Better")
                        Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.top_logo),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                backgroundColor = white
            )
        },
        content = { paddingValues ->
            Column{
                Text(modifier = Modifier.padding(top=10.dp, start = 23.dp),
                    text = "진행중인 솔루션",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(17.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(learnerProfiles) { profile ->
                        LearnerProfileCard(profile = profile)
                        SolutionBox(solutions = solutions)
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SolutionItem(
    solution: Solution,
    onClick: (Solution) -> Unit
) {
    // Use Card composable to group solution information visually
    Card(
        onClick = { onClick(solution) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = 0.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = solution.solutionImage),
                contentDescription = null,
                modifier = Modifier.size(33.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = solution.solutionTitle,
                    style = MaterialTheme.typography.body1,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "진행 단계: ${solution.stage}회기",
                    style = MaterialTheme.typography.caption,
                    fontSize = 11.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    Modifier.size(15.dp)
            )

        }
    }
}

@Composable
fun LearnerProfileCard(
    profile: LearnerProfile,
) {
    Row(
        modifier = Modifier.padding(start = 23.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = profile.photoUrl),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(0.5.dp, Color.Black, CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = profile.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "학습자",
                    fontSize = 15.sp,)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${profile.age}세",
                    fontSize = 12.sp)
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "/",
                    fontSize = 15.sp)
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "${profile.gender}",
                    fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SolutionBox(
    solutions: List<Solution>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 9.dp)
            .border(1.dp, bg3, RoundedCornerShape(15.dp)),
        backgroundColor = Color.White,
    ) {
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            solutions.forEachIndexed { index, solution ->
                SolutionItem(solution = solution, onClick = {})
                if (index < solutions.lastIndex) {
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}
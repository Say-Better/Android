import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.model.SolutionSymbol
import gdsc.solutionchallenge.saybetter.presentation.theme.bg3
import gdsc.solutionchallenge.saybetter.presentation.theme.black
import gdsc.solutionchallenge.saybetter.presentation.theme.green
import gdsc.solutionchallenge.saybetter.presentation.theme.more_green
import gdsc.solutionchallenge.saybetter.presentation.theme.white



@Preview
@Composable
fun ProgressScreen(){
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
            Column(modifier = Modifier.padding(paddingValues)){
                Row(verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(start = 26.dp, top=30.dp)){
                    Column(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "중재 단계 5회기",
                            style = MaterialTheme.typography.body1,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "TV보는 상황 솔루션",
                            style = MaterialTheme.typography.caption,
                            fontSize = 14.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(130.dp))

                    Button(
                        onClick = { /*TODO: Handle onClick*/ },
                        modifier = Modifier
                            .width(52.dp)
                            .height(30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = green),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("완료",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = white
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(261.dp)
                    .background(bg3)
                    ) {

                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 32.dp, start = 9.dp)) {
                    TimeButton()
                    Spacer(modifier = Modifier.width(82.dp))
                    ThreeButton()
                }

                CardProgress1()

            }
        }
    )
}

@Composable
fun CardProgress1() {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_left),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        CardItem1()
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_right),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun CardProgress2() {
    Spacer(modifier = Modifier.height(80.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_left),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        CardItem2()
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_right),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun CardProgress3() {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_left),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        CardItem3()
        Image(
            painter = painterResource(id = R.drawable.ic_card_arrow_right),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}


@Composable
fun CardItem1(){
    Spacer(modifier = Modifier.width(30.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.card_img),
            contentDescription = null,
            modifier = Modifier.size(240.dp)
        )
        Text("00로갑시다",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = black,
        )
    }
    Spacer(modifier = Modifier.width(30.dp))
}

@Composable
fun CardItem2(){
    val solutionsymbol = listOf(
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        ),
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        )
    )
    Spacer(modifier = Modifier.width(27.dp))
    LazyRow{items(solutionsymbol){item->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = item.solutionImage),
                contentDescription = null,
                modifier = Modifier.size(124.dp)
            )
            Text(item.solutionTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = black,
            )
        }
    }
        
    }
    Spacer(modifier = Modifier.width(27.dp))
}

@Composable
fun CardItem3(){
    val solutionsymbol = listOf(
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        ),
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        ),
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        ),
        SolutionSymbol(
            solutionImage = R.drawable.card_img,
            solutionTitle = "00에갑시다"
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        for (i in 1..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)) {
                for (j in 1..2) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(8.dp)
                    ) {
                        Text(text = "Item $i$j", modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }

}


@Composable
fun TimeButton(){

    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = bg3,
            shape = RoundedCornerShape(10.dp)
        )
        .width(100.dp)
        .height(40.dp)
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 7.dp, start = 10.dp)
            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
            )
            Text(text = "00:10", fontSize = 16.sp, fontWeight = FontWeight.Bold
            , color = black, modifier = Modifier.padding(start = 5.dp)
            )
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = more_green,
                shape = RoundedCornerShape(10.dp)
            )
            .width(70.dp)
            .height(40.dp)
    ) {
        Text(
            text = "1/10",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = white,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
    }
}


@Composable
fun ThreeButton() {
    val numbers = listOf(1, 2, 4)
    val selectedIndex = remember { mutableIntStateOf(0) } // 선택된 버튼의 인덱스를 저장할 상태 변수

    Row {
        numbers.forEachIndexed { index, number ->
            val isSelected = selectedIndex.value == index

            NumberButton(
                number = number,
                isSelected = isSelected
            ) {
                selectedIndex.value = index
            }

            Spacer(modifier = Modifier.width(22.dp))
        }
    }
}

@Composable
fun NumberButton(
    number: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) more_green else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        modifier = Modifier
            .width(20.dp)
            .height(20.dp)
            .background(
                color = backgroundColor,
                shape = when (number) {
                    1 -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    4 -> RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    else -> RectangleShape
                },
            )
            .border(
                width = 1.dp,
                color = bg3,
                shape = when (number) {
                    1 -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    4 -> RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    else -> RectangleShape
                }
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = number.toString(),
                color = textColor,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

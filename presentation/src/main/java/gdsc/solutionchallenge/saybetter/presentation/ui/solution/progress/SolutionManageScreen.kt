package gdsc.solutionchallenge.saybetter.presentation.ui.solution.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.model.SolutionProgress
import gdsc.solutionchallenge.saybetter.presentation.model.SolutionSymbol
import gdsc.solutionchallenge.saybetter.presentation.theme.bg3
import gdsc.solutionchallenge.saybetter.presentation.theme.black
import gdsc.solutionchallenge.saybetter.presentation.theme.green
import gdsc.solutionchallenge.saybetter.presentation.theme.light_green
import gdsc.solutionchallenge.saybetter.presentation.theme.white


val solutionsymbol = listOf(
    SolutionSymbol(
        solutionImage = R.drawable.symbol_1,
        solutionTitle = "00에갑시다"
    ),
    SolutionSymbol(
        solutionImage = R.drawable.symbol_1,
        solutionTitle = "00에갑시다"
    ),
    SolutionSymbol(
        solutionImage = R.drawable.symbol_1,
        solutionTitle = "00에갑시다"
    )
)

@Preview
@Composable
fun SolutionManageScreen() {
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
                            text = "TV보는 상황 솔루션",
                            style = MaterialTheme.typography.body1,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "중재 단계 4회기 진행중",
                            style = MaterialTheme.typography.caption,
                            fontSize = 14.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(95.dp))

                    Button(
                        onClick = { /*TODO: Handle onClick*/ },
                        modifier = Modifier
                            .width(52.dp)
                            .height(30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = green),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("진행",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = white)
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Box(modifier = Modifier.padding(start = 40.dp)) {
                   Graph()
                }
                Text(modifier = Modifier.padding(top=69.dp, start = 28.dp),
                    text = "솔루션 상징 목록",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 38.dp)
                ) {
                    items(solutionsymbol) { symbol ->
                        SymbolItem(symbol = symbol)
                    }
                }
            }
        }
    )
}
@Composable
fun Graph(){
    val progressData = listOf(
        SolutionProgress("02-01", 0),
        SolutionProgress("02-02", 4),
        SolutionProgress("02-03", 3),
        SolutionProgress("02-04", 5)
    )
    val maxValue = 10

    SolutionProgressChart(
        progressData = progressData,
        maxValue = maxValue
    )
}

@Composable
fun SolutionProgressChart(
    progressData: List<SolutionProgress>,
    maxValue: Int
) {
    val density = LocalDensity.current
    val chartWidth = 300.dp
    val chartHeight = 203.dp
    val chartWidthPx = with(density) { chartWidth.roundToPx().toFloat() }
    val chartHeightPx = with(density) { chartHeight.roundToPx().toFloat() }
    val pointRadius = 4.dp
    val pointRadiusPx = with(density) { pointRadius.roundToPx().toFloat() }
    val lineStrokeWidth = 2.dp
    val lineStrokeWidthPx = with(density) { lineStrokeWidth.roundToPx().toFloat() }
    val grayLineStrokeWidth = 1.dp
    val grayLineStrokeWidthPx = with(density) { grayLineStrokeWidth.roundToPx().toFloat() }

    Box(
        modifier = Modifier
            .width(chartWidth)
            .height(chartHeight)
            .background(white)
    ) {
        val points = remember(progressData) {
            progressData.mapIndexed { index, progress ->
                val x = index / (progressData.size - 1).toFloat() * chartWidthPx
                val y = (1 - (progress.frequency.toFloat() / maxValue)) * chartHeightPx
                Offset(x, y)
            }
        }

        // Draw horizontal gray lines and Y-axis labels
        (0..maxValue step 2).forEachIndexed { index, value ->
            val y = (1 - value / maxValue.toFloat()) * chartHeightPx
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = if (index== 0)black else bg3,
                    start = Offset(0f, y),
                    end = Offset(chartWidthPx, y),
                    strokeWidth = if (index == 0) grayLineStrokeWidthPx * 2 else grayLineStrokeWidthPx
                )
            }
            Text(
                text = value.toString(),
                modifier = Modifier.offset(x = with(density) { -20.dp }, y = with(density) { y.toDp() - 6.dp }),
                fontSize = 12.sp,
                textAlign = TextAlign.End,  // Align text to the right
                color = black
            )
        }

        // Draw vertical gray dotted lines and X-axis labels
        points.forEachIndexed { index, point ->
            if (index != 0) {  // Exclude the first vertical line (x-axis)
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        start = Offset(point.x, 0f),
                        end = Offset(point.x, chartHeightPx),
                        color = bg3,
                        strokeWidth = grayLineStrokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                }
            }
            Text(
                text = progressData[index].date,
                modifier = Modifier.offset(x = with(density) { point.x.toDp() - 10.dp }, y = with(density) { chartHeightPx.toDp() + 10.dp }),
                fontSize = 12.sp,
                color = black
            )
        }

        // Draw line
        points.zipWithNext().forEach { (start, end) ->
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = black,
                    start = start,
                    end = end,
                    strokeWidth = lineStrokeWidthPx
                )
            }
        }

        // Draw circle at the last point
        val lastPoint = points.last()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = black,
                center = lastPoint,
                radius = pointRadiusPx
            )
        }
    }
}


@Composable
fun SymbolItem(symbol: SolutionSymbol){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = symbol.solutionImage),
            contentDescription = null,
            modifier = Modifier.size(67.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = symbol.solutionTitle,
            fontSize =15.sp )


    }
}
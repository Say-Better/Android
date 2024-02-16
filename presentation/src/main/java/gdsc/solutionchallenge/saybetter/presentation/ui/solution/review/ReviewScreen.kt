import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gdsc.solutionchallenge.saybetter.R
import gdsc.solutionchallenge.saybetter.presentation.model.LogItem
import gdsc.solutionchallenge.saybetter.presentation.model.PlaybackState
import gdsc.solutionchallenge.saybetter.presentation.theme.bg2
import gdsc.solutionchallenge.saybetter.presentation.theme.bg3
import gdsc.solutionchallenge.saybetter.presentation.theme.black
import gdsc.solutionchallenge.saybetter.presentation.theme.green
import gdsc.solutionchallenge.saybetter.presentation.theme.light_green
import gdsc.solutionchallenge.saybetter.presentation.theme.pink
import gdsc.solutionchallenge.saybetter.presentation.theme.white


val logItems = listOf(
    LogItem(img = R.drawable.symbol_1 , timestamp = "00:00", content = "00에갑시다"),
    LogItem(img = R.drawable.symbol_1 ,timestamp = "00:10", content = "00에갑시다"),
    LogItem(img = R.drawable.symbol_1 ,timestamp = "00:20", content = "00에갑시다"),
    LogItem(img = R.drawable.symbol_1 ,timestamp = "00:30", content = "00에갑시다"),
)
@Preview
@Composable
fun ReviewScreen(){

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
            val playbackState = remember { mutableStateOf(PlaybackState(currentTime = 0L, isPlaying = false)) }
            Column(modifier = Modifier.padding(paddingValues)){
                Text(
                    text = "각 응답에 대한 반응을",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 26.dp, top = 30.dp)
                )
                Text(
                    text = "정반응/오반응으로 나타내 주세요.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 26.dp)
                )
                Text(
                    text = "중재 단계 4회기 리뷰",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 26.dp , top = 3.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 45.dp, top = 47.dp)
                ) {
                    Text(
                        text = "1/10",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    PlayReview()
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        TwoButton()
                    }
                }
                Text(
                    text = "상징 터치로그",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 43.dp, start = 26.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                // 재생 상태에 따라 로그 아이템 필터링
                val visibleLogItems = if (playbackState.value.isPlaying) {
                    // 재생 중에는 현재 재생 시간 이후의 항목만 표시
                    logItems.filter { it.timestamp >= playbackState.value.currentTime.toString() }
                } else {
                    // 재생 중지 중에는 모든 항목 표시
                    logItems
                }
                LogList(logItems = visibleLogItems)
            }
        }
    )
}


@Composable
fun TwoButton(){
    Button(
        onClick = { /*TODO: Handle onClick*/ },
        modifier = Modifier
            .width(129.dp)
            .height(48.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = pink),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text("오반응",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = black)
    }
    Spacer(modifier = Modifier.width(16.dp))

    Button(
        onClick = { /*TODO: Handle onClick*/ },
        modifier = Modifier
            .width(129.dp)
            .height(48.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = light_green),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text("정반응",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = black)
    }
}

@Composable
fun PlayReview(){

    val playbackState = remember { mutableStateOf(PlaybackState(currentTime = 0L, isPlaying = false)) }
    val onPlayPause: (Boolean) -> Unit = remember { { playing: Boolean ->
        playbackState.value = playbackState.value.copy(isPlaying = playing)
    } }
    val onGoToNextItem: () -> Unit = remember { {
        val currentIndex = logItems.indexOfFirst { it.timestamp >= playbackState.value.currentTime.toString() }
        if (currentIndex == -1 || currentIndex == logItems.lastIndex) {
            // 마지막 항목이거나 현재 위치를 찾을 수 없으면 처음으로 돌아갑니다.
            playbackState.value = playbackState.value.copy(currentTime = 0L)
        } else {
            // 그렇지 않으면 다음 항목의 시작 시간으로 이동합니다.
            playbackState.value = playbackState.value.copy(currentTime = logItems[currentIndex + 1].timestamp.toLong())
        }
    } }
    val onSeekTo: (Long) -> Unit = remember { { time: Long ->
        playbackState.value = playbackState.value.copy(currentTime = time)
    } }

    val onGoToPrevItem: () -> Unit = remember { {
        val currentIndex = logItems.indexOfFirst { it.timestamp >= playbackState.value.currentTime.toString() }
        if(currentIndex == -1 || currentIndex == 0){
            // 첫 번째 항목이거나 현재 위치를 찾을 수 없으면 마지막으로 돌아갑니다.
            playbackState.value = playbackState.value.copy(currentTime = logItems.last().timestamp.toLong())
        } else {
            // 그렇지 않으면 이전 항목의 시작 시간으로 이동합니다.
            playbackState.value = playbackState.value.copy(currentTime = logItems[currentIndex - 1].timestamp.toLong())
        }
    } }


    Play(logItems, playbackState, onPlayPause, onGoToNextItem, onGoToPrevItem, onSeekTo)



}

@Composable
fun Play(
    logItems: List<LogItem>,
    playbackState: MutableState<PlaybackState>,
    onPlayPause: (Boolean) -> Unit,
    onGoToNextItem: () -> Unit,
    onGoToPrevItem: () -> Unit,
    onSeekTo: (Long) -> Unit
){

    Box(
        modifier = Modifier
            .width(301.dp)
            .height(125.dp)
            .border(1.dp, bg3, shape = RoundedCornerShape(10.dp))
    ) {
        Column {
            // 재생 버튼 및 항목 간 이동 버튼
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                PrevItemButton(onGoToPrevItem = onGoToPrevItem)
                Spacer(modifier = Modifier.width(40.dp))
                PlayButton(playbackState = playbackState, onPlayPause = onPlayPause)
                Spacer(modifier = Modifier.width(40.dp))
                NextItemButton(onGoToNextItem = onGoToNextItem)
            }

            // 슬라이드 바
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                PlaybackSlider(playbackState = playbackState, onSeekTo = onSeekTo, maxDuration = 60)
            }

            // 음성 및 터치 로그 목록
            /*val visibleLogItems = if (playbackState.value.isPlaying) {
                // 재생 중에는 현재 재생 시간 이후의 항목만 표시
                logItems.filter { it.timestamp >= playbackState.value.currentTime.toString() }
            } else {
                // 재생 중지 중에는 모든 항목 표시
                logItems
            }
            LogList(logItems = visibleLogItems)*/
        }
    }
}


// 이전 항목으로 이동 버튼 컴포저블
@Composable
fun PrevItemButton(
    onGoToPrevItem: () -> Unit
) {
    IconButton(onClick = onGoToPrevItem) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_start),
            contentDescription = null,
        )
    }
}


// 재생 버튼 컴포저블
@Composable
fun PlayButton(
    playbackState: MutableState<PlaybackState>,
    onPlayPause: (Boolean) -> Unit
) {
    val icon = if (playbackState.value.isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow
    IconButton(onClick = { onPlayPause(!playbackState.value.isPlaying) }) {
        Icon(icon, contentDescription = "Play/Pause", modifier = Modifier.size(30.dp))
    }
}

// 항목 간 이동 버튼 컴포저블
@Composable
fun NextItemButton(
    onGoToNextItem: () -> Unit
) {
    IconButton(onClick = onGoToNextItem) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_end),
            contentDescription = null,
        )
    }
}

// 슬라이드 바 컴포저블
@Composable
fun PlaybackSlider(
    playbackState: MutableState<PlaybackState>,
    onSeekTo: (Long) -> Unit,
    maxDuration: Long
) {
    val sliderValue = playbackState.value.currentTime.toFloat() / maxDuration
    Slider(
        value = sliderValue,
        onValueChange = { onSeekTo((it * maxDuration).toLong()) },
        valueRange = 0f..1f,
        colors = SliderDefaults.colors(
            thumbColor = bg2,
            activeTrackColor = black,
            inactiveTrackColor = bg3
        ),
        modifier = Modifier.width(233.dp)
    )
}

@Composable
fun LogList(logItems: List<LogItem>) {
    LazyColumn {
        items(logItems) { logItem ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 29.dp)) {
                Image(
                    painter = painterResource(id = logItem.img), // symbolImageResId는 LogItem 클래스에 추가된 필드입니다.
                    contentDescription = null,
                    modifier = Modifier.size(66.dp)
                )
                Text(text = logItem.content, fontSize = 15.sp, modifier = Modifier.padding(start = 8.dp))
                Text(text = logItem.timestamp, fontSize = 12.sp, color = bg3, modifier = Modifier.padding(start = 140.dp))
            }
        }
    }
}



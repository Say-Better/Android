package gdsc.solutionchallenge.saybetter.presentation.model

data class SolutionSymbol(
    val solutionImage: Int,
    val solutionTitle: String,
)

data class SolutionProgress(
    val date: String,
    val frequency: Int
)

data class LogItem
    (val img : Int, val timestamp: String, val content: String)

data class PlaybackState( val isPlaying: Boolean, val currentTime: Long)
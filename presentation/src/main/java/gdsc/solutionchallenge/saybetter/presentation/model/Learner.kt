package gdsc.solutionchallenge.saybetter.presentation.model

data class LearnerProfile(
    val name: String,
    val photoUrl: Int,
    val age: Int,
    val gender: String
)

data class Solution(
    val solutionImage: Int,
    val solutionTitle: String,
    val stage: Int
)
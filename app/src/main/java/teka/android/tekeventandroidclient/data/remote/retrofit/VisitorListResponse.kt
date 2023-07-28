package teka.android.tekeventandroidclient.data.remote.retrofit

import kotlinx.serialization.Serializable

@Serializable
data class VisitorListResponse(
    val page: Int,
    val results: List<VisitorListResult>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class VisitorListResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

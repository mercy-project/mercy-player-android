package sideproject.mercy.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchBookResponse(
    @SerialName("documents") val documents: List<Document>?,
    @SerialName("meta") val meta: Meta?
)

@Serializable
data class Document(
    @SerialName("authors") val authors: List<String>?,
    @SerialName("translators") val translators: List<String>?,
    @SerialName("contents") val contents: String?,
    @SerialName("datetime") val datetime: String?,
    @SerialName("price") val price: Int?,
    @SerialName("publisher") val publisher: String?,
    @SerialName("sale_price") val sale_price: Int?,
    @SerialName("status") val status: String?,
    @SerialName("thumbnail") val thumbnail: String?,
    @SerialName("title") val title: String?,
    @SerialName("url") val url: String?
)

@Serializable
data class Meta(
    @SerialName("is_end") val isEnd: Boolean?,
    @SerialName("total_count") val totalCount: Int?
)
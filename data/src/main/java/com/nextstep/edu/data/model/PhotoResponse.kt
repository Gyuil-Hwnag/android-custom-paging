package com.nextstep.edu.data.model

import com.google.gson.annotations.SerializedName

internal data class PhotoResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("author")
    val author: String?,

    @SerializedName("url")
    val url: String?
)

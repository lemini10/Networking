package com.example.networking

import com.google.gson.annotations.SerializedName

data class ResponseClass(
    var status: String,
    var copyright: String,
    var num_results: Int,
    var results: List<ResultClass>) {}

data class ResultClass(
    var title: String,
    var published_date: String,
    var media: List<MediaResponse>,
    var url: String) {}

data class MediaResponse(
    @SerializedName("media-metadata") var mediaMetaData: List<MediaDataResponse>
) {}

data class MediaDataResponse(
    var url: String
){}
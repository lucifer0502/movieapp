package com.appfer.movieapp.models
import com.google.gson.annotations.SerializedName

data class Moviemodel(
    @SerializedName("id")
    var id : String,
    @SerializedName("original_title")
    var title: String,
    @SerializedName("overview")
    var overview : String,
    @SerializedName("poster_path")
    var image : String,
    @SerializedName("popularity")
    var popularity: String,
    @SerializedName("vote_average")
    var rating: String
)
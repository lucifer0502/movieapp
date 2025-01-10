package com.appfer.movieapp.network.response

import com.appfer.movieapp.models.Moviemodel
import com.google.gson.annotations.SerializedName

data class movieresponse(
    @SerializedName("results")
    var results :  List<Moviemodel>
)
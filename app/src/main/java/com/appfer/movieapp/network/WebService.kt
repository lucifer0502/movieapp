package com.appfer.movieapp.network

import com.appfer.movieapp.network.response.movieresponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("now_playing")
    suspend fun getallmovies(
        @Query ("api_key") apiKey:String
    ): Response<movieresponse>

    @GET("popular")
    suspend fun getpopular(
        @Query ("api_key") apiKey:String
    ): Response<movieresponse>

    @GET("top_rated")
    suspend fun getrated(
        @Query ("api_key") apiKey:String
    ): Response<movieresponse>

    @GET("upcoming")
    suspend fun getupcoming(
        @Query ("api_key") apiKey:String
    ): Response<movieresponse>




}
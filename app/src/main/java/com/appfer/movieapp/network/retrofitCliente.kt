package com.appfer.movieapp.network

import com.appfer.movieapp.core.constantes
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitCliente {

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(constantes.API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}
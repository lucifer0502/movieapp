package com.appfer.movieapp.viewsmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appfer.movieapp.core.constantes
import com.appfer.movieapp.models.Moviemodel
import com.appfer.movieapp.network.retrofitCliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class moviesviewmodel: ViewModel() {

    private var movielist = MutableLiveData<List<Moviemodel>>()
    val moviesList: LiveData<List<Moviemodel>> = movielist

    fun getallmovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitCliente.webService.getallmovies(constantes.API_KEY)
            withContext(Dispatchers.Main) {
                movielist.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }

    fun getpopular() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitCliente.webService.getpopular(constantes.API_KEY)
            withContext(Dispatchers.Main) {
                movielist.value = response.body()!!.results.sortedByDescending { it.popularity.toFloat() }
            }

        }

    }

    fun gettoprated() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitCliente.webService.getrated(constantes.API_KEY)
            withContext(Dispatchers.Main) {
                movielist.value = response.body()!!.results.sortedByDescending { it.rating.toFloat() }
            }

        }

    }

    fun getupcoming() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitCliente.webService.getupcoming(constantes.API_KEY)
            withContext(Dispatchers.Main) {
                movielist.value = response.body()!!.results.sortedBy { it.title }
            }

        }

    }
}
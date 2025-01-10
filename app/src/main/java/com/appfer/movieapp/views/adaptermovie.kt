package com.appfer.movieapp.views

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.appfer.movieapp.R
import com.appfer.movieapp.core.constantes
import com.appfer.movieapp.models.Moviemodel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

class adaptermovie (
    val context: Context,
    var moviesList: List<Moviemodel>

    ): RecyclerView.Adapter<adaptermovie.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvMovie = itemView.findViewById(R.id.cvMovie) as CardView
        val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
        val tvinfo = itemView.findViewById(R.id.tvInfo) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rvmovie, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return moviesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val movie = moviesList[position]

        Glide.with(context)
            .load("${constantes.IMAGE_URL}${movie.image}")
            .apply(RequestOptions().override(constantes.IMAGE_WIDHT,constantes.IMAGE_HEIGHT))
            .into(holder.ivImage)

        holder.tvinfo.text = HtmlCompat.fromHtml(
            "<b> Tittle: </b>" + movie.title +
            "<br><b> Popularity: </b>" + movie.popularity +
                    "<br><b> Rating: </b>" + movie.rating,
            HtmlCompat.FROM_HTML_MODE_LEGACY

        )
        
        holder.cvMovie.setOnClickListener{
            showOverView(movie.title, movie.overview)
        }
    }

    private fun showOverView(title: String, overview: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(overview)
       builder.show()
        }

    }


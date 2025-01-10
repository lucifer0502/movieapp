package com.appfer.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.appfer.movieapp.databinding.FragmentMovieBinding
import com.appfer.movieapp.views.adaptermovie
import com.appfer.movieapp.viewsmodels.moviesviewmodel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: moviesviewmodel
    private lateinit var adaptermovie: adaptermovie
    private lateinit var btnGetAll: Button
    private lateinit var btnGetPopular: Button
    private lateinit var btnTopRated: Button
    private lateinit var btnGetUpcoming: Button
    private lateinit var tvcategory: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar las vistas
        btnGetAll = binding.btnGetAll
        btnGetPopular = binding.btnGetPopular
        btnTopRated = binding.btnGetTopRated
        btnGetUpcoming = binding.btnGetUpcoming
        tvcategory = binding.categoriatv

        viewModel = ViewModelProvider(this)[moviesviewmodel::class.java]
        setupRecyclerView()


        tvcategory.text = "INICIO"
        viewModel.getallmovies()

        // Observadores y eventos
        viewModel.moviesList.observe(viewLifecycleOwner) {
            adaptermovie.moviesList = it
            adaptermovie.notifyDataSetChanged()
        }

        btnGetAll.setOnClickListener {
            tvcategory.text = "ESTRENOS"
            viewModel.getallmovies()
        }

        btnGetPopular.setOnClickListener {
            tvcategory.text = "POPULARES"
            viewModel.getpopular()
        }

        btnTopRated.setOnClickListener {
            tvcategory.text = "TOP"
            viewModel.gettoprated()
        }

        btnGetUpcoming.setOnClickListener {
            tvcategory.text = "PRÓXIMOS"
            viewModel.getupcoming()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovies.layoutManager = layoutManager
        adaptermovie = adaptermovie(requireContext(), arrayListOf())
        binding.rvMovies.adapter = adaptermovie
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evitar fugas de memoria
    }
}

package com.example.test_lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.test_lab_week_12.model.Movie

class MovieViewModel(private val movieRepository: MovieRepository)
    : ViewModel() {
    init {
        fetchPopularMovies()
    }
    // define the LiveData
    val popularMovies: LiveData<List<Movie>>
        get() = movieRepository.movies
    val error: LiveData<String>
        get() = movieRepository.error
    // fetch movies from the API
    private fun fetchPopularMovies() {
        // launch a coroutine in viewModelScope (Dispatchers.IO)
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
        }
    }
}
package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx" // replace with your TMDB API key

    // returns a Flow that emits a list of popular movies
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // call the suspend network function and emit the results
            val response = movieService.getPopularMovies(apiKey)
            // sort descending by popularity to preserve prior filtering behavior
            val sorted = response.results.sortedByDescending { it.popularity }
            emit(sorted)
        }.flowOn(Dispatchers.IO)
    }
}
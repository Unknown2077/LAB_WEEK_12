package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"

    // returns a Flow that emits a list of popular movies
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // call the suspend network function and emit the results
            val response = movieService.getPopularMovies(apiKey)
            emit(response.results)
        }.flowOn(Dispatchers.IO)
    }
}
package com.example.moderandomjokes.model.api

import com.example.moderandomjokes.model.DataJokes
import io.reactivex.Single
import retrofit2.http.GET

interface JokesApi {
    @GET("jokes/random/30?exclude=[explicit]")
    fun fetchJokes(): Single<DataJokes>
}
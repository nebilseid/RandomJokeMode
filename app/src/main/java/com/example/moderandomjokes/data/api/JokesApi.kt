package com.example.moderandomjokes.data.api

import com.example.moderandomjokes.model.DataJokes
import io.reactivex.Single
import retrofit2.http.GET

interface JokesApi {
    @GET("jokes/random/100")
    fun fetchJokes(): Single<DataJokes>
}
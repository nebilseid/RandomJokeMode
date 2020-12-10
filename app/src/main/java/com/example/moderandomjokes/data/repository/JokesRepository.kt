package com.example.moderandomjokes.data.repository

import com.example.moderandomjokes.model.DataJokes
import com.example.moderandomjokes.data.api.JokesApi
import io.reactivex.Single

interface Repository {
    fun fetchJokes(): Single<DataJokes>
}

class JokesRepository(private val jokesApi: JokesApi) : Repository {
    override fun fetchJokes(): Single<DataJokes> = jokesApi.fetchJokes()
}
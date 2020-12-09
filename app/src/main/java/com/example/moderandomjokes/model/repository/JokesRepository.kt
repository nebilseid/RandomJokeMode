package com.example.moderandomjokes.model.repository

import com.example.moderandomjokes.model.DataJokes
import com.example.moderandomjokes.model.api.JokesApi
import io.reactivex.Single

interface RepositoryImpl {
    fun fetchJokes(): Single<DataJokes>
}

class JokesRepository(private val jokesApi: JokesApi) : RepositoryImpl {
    override fun fetchJokes(): Single<DataJokes> = jokesApi.fetchJokes()
}
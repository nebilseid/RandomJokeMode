package com.example.moderandomjokes.data.repository

import com.example.moderandomjokes.model.DataJokes
import com.example.moderandomjokes.model.Value
import io.reactivex.Single

object FakeRepository: Repository {
    override fun fetchJokes(): Single<DataJokes> = Single.just(DataJokes("mock", generateMockedValues()))

    private fun generateMockedValues(): List<Value> =
        (1..10).map {
            val categories: List<String> = if( it % 2 == 0) listOf("explicit") else listOf("nerdy")
            val joke = "Joke $it"
            Value(
                categories,
                it,
                joke
            )
        }
}
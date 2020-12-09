package com.example.moderandomjokes.model

data class DataJokes(
    val type: String,
    val value: List<Value>
)

data class Value(
    val categories: List<String>,
    val id: Int,
    val joke: String
)
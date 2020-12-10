package com.example.moderandomjokes.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moderandomjokes.model.DataJokes
import com.example.moderandomjokes.model.Value
import com.example.moderandomjokes.data.repository.Repository
import com.example.moderandomjokes.util.AppSchedulers
import com.example.moderandomjokes.util.asLiveData
import io.reactivex.disposables.CompositeDisposable

class JokesViewModel(private val repository: Repository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _loadingLiveData = MutableLiveData<Boolean>()
    private val _jokesContentLiveData = MutableLiveData<List<Value>>()
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()

    val loadingLiveData = _loadingLiveData.asLiveData()
    val jokesContentLiveData = _jokesContentLiveData.asLiveData()
    val errorLiveData = _errorLiveData.asLiveData()

    fun fetchJokes() {
        disposable.add(
            repository.fetchJokes()
                .subscribeOn(AppSchedulers.io)
                .observeOn(AppSchedulers.ui)
                .doOnSubscribe { _loadingLiveData.value = true }
                .doOnEvent { _, _ -> _loadingLiveData.value = false }
                .subscribe(
                    { _jokesContentLiveData.value = filterJokes(it, "explicit") },
                    { handleError(it) }
                )
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    private fun filterJokes(jokes: DataJokes, filter: String = "") =
        jokes.value.filter { joke -> joke.categories.any { it != filter } }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        _errorLiveData.value = error.message
    }
}
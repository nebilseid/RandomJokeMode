package com.example.moderandomjokes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moderandomjokes.model.DataJokes
import com.example.moderandomjokes.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JokesViewModel(private val repository: RepositoryImpl) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val jokesContentLiveData = MutableLiveData<DataJokes>()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()


    fun fetchJokes() {
        disposable.add(
            repository.fetchJokes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingLiveData.value = true }
                .doOnEvent { _, _ -> loadingLiveData.value = false }
                .subscribe({
                    jokesContentLiveData.value = it
                }, {
                    it.printStackTrace()
                    errorLiveData.value = "ERROR"
                })
        )
    }
    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun getLoadingObservable(): LiveData<Boolean> = loadingLiveData
    fun getJokesContentObservable(): MutableLiveData<DataJokes> = jokesContentLiveData
    fun getErrorObservable(): LiveData<String> = errorLiveData
}
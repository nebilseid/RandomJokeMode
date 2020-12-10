package com.example.moderandomjokes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moderandomjokes.data.repository.FakeRepository
import com.example.moderandomjokes.model.Value
import com.example.moderandomjokes.data.repository.Repository
import com.example.moderandomjokes.util.AppSchedulers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`


class JokesViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val mockRepository = mock<Repository>()
    private lateinit var viewModel: JokesViewModel

    @Before
    fun setUp() {
        viewModel = JokesViewModel(mockRepository)
        AppSchedulers.io = Schedulers.trampoline()
        AppSchedulers.ui = Schedulers.trampoline()
    }

    @Test
    fun `test loading Progressbar is shown`() {
        // Prepare test
        val observer = mock<Observer<Boolean>>()
        viewModel.loadingLiveData.observeForever(observer)
        `when`(mockRepository.fetchJokes()).thenReturn(FakeRepository.fetchJokes())

        // Trigger test
        viewModel.fetchJokes()

        // Verify expected outcome
        verify(observer).onChanged(true)
    }

    @Test
    fun `test content is fetched and filtered`() {
        // Prepare test
        val expectedSize = 5 // Half of the jokes mocked should have been removed
        val observer = mock<Observer<List<Value>>>()
        viewModel.jokesContentLiveData.observeForever(observer)
        `when`(mockRepository.fetchJokes()).thenReturn(FakeRepository.fetchJokes())

        // Trigger test
        viewModel.fetchJokes()

        // Verify expected outcome
        Assert.assertEquals(expectedSize, viewModel.jokesContentLiveData.value?.size)
    }

    @Test
    fun `test error loading content`() {
        // Prepare test
        val errorMessage = "Error"
        val observer = mock<Observer<String>>()
        viewModel.errorLiveData.observeForever(observer)
        `when`(mockRepository.fetchJokes()).thenReturn(Single.error(Throwable(errorMessage)))

        // Trigger test
        viewModel.fetchJokes()

        // Verify expected outcome
        verify(observer).onChanged(errorMessage)
    }
}
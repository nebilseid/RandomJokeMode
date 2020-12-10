package com.example.moderandomjokes.di

import com.example.moderandomjokes.data.repository.JokesRepository
import com.example.moderandomjokes.data.repository.Repository
import com.example.moderandomjokes.ui.JokesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    factory { JokesApiFactory.retrofitJokest() }

}

val repo: Module = module {
    single<Repository> { JokesRepository(get()) }
}


val viewModels = module {
    viewModel {
        JokesViewModel(repository = get())
    }
}
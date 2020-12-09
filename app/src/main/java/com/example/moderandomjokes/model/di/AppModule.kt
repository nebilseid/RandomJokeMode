package com.example.moderandomjokes.model.di

import com.example.moderandomjokes.model.repository.JokesRepository
import com.example.moderandomjokes.model.repository.RepositoryImpl
import com.example.moderandomjokes.ui.JokesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    factory { JokesApiFactory.retrofitJokest() }

}

val repo: Module = module {
    single<RepositoryImpl> { JokesRepository(get()) }
}


val viewModels = module {
    viewModel {
        JokesViewModel(repository = get())
    }
}
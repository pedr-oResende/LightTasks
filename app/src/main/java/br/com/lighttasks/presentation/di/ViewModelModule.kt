package br.com.lighttasks.presentation.di

import br.com.lighttasks.presentation.screens.home.HomeViewModel
import br.com.lighttasks.presentation.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel {
        HomeViewModel(
            getTasksUseCase = get()
        )
    }

    viewModel {
        LoginViewModel(
            loginUseCase = get(),
            validateUsername = get(),
            validatePassword = get()
        )
    }

}
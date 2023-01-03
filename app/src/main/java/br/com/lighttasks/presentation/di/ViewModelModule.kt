package br.com.lighttasks.presentation.di

import br.com.lighttasks.presentation.screens.task_list.TasksViewModel
import br.com.lighttasks.presentation.screens.login.LoginViewModel
import br.com.lighttasks.presentation.screens.register.RegisterViewModel
import br.com.lighttasks.presentation.screens.task_detail.TaskDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel {
        TasksViewModel(
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

    viewModel {
        RegisterViewModel(
            registerUseCase = get(),
            validateUsername = get(),
            validatePassword = get()
        )
    }

    viewModel {
        TaskDetailViewModel()
    }

}
package br.com.lighttasks.domain.di

import br.com.lighttasks.domain.usecase.user.*
import org.koin.dsl.module

val useCaseModules = module {

    single { RegisterUseCase(get()) }

    single { LoginUseCase(get()) }

    single { GetUserUseCase(get()) }

    single { EditUserUseCase(get()) }

    single { DeleteUserUseCase(get()) }

}
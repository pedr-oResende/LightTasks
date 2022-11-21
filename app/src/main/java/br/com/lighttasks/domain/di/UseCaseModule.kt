package br.com.lighttasks.domain.di

import br.com.lighttasks.domain.usecase.basic_user.AddTeamMemberUseCase
import br.com.lighttasks.domain.usecase.basic_user.EditBasicUserUseCase
import br.com.lighttasks.domain.usecase.basic_user.GetBasicUserUseCase
import br.com.lighttasks.domain.usecase.basic_user.RemoveTeamMemberUseCase
import br.com.lighttasks.domain.usecase.task.*
import br.com.lighttasks.domain.usecase.user.*
import org.koin.dsl.module

val useCaseModules = module {

    single { RegisterUseCase(get()) }

    single { LoginUseCase(get()) }

    single { GetUserUseCase(get()) }

    single { EditUserUseCase(get()) }

    single { DeleteUserUseCase(get()) }

    single { GetBasicUserUseCase(get()) }

    single { EditBasicUserUseCase(get()) }

    single { AddTeamMemberUseCase(get()) }

    single { AddTeamMemberUseCase(get()) }

    single { RemoveTeamMemberUseCase(get()) }

    single { CreateTaskUseCase(get()) }

    single { DeleteTaskUseCase(get()) }

    single { EditTaskUseCase(get()) }

    single { GetTasksByIdUseCase(get()) }

    single { GetTasksByUserUseCase(get()) }

}
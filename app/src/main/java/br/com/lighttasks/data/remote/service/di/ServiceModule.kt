package br.com.lighttasks.data.remote.service.di

import br.com.lighttasks.BuildConfig
import br.com.lighttasks.data.remote.service.*
import br.com.lighttasks.data.remote.service.ServiceManager
import org.koin.dsl.module

val apiModules = module {

    single { ServiceManager(BuildConfig.BASE_URL) }

    single { get<ServiceManager>().create(UserService::class.java) }

    single { get<ServiceManager>().create(BasicUserService::class.java) }

    single { get<ServiceManager>().create(TaskService::class.java) }

    single { get<ServiceManager>().create(TeamService::class.java) }
}

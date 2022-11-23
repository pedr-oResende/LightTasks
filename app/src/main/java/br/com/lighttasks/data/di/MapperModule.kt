package br.com.lighttasks.data.di

import br.com.lighttasks.commom.mapper.NullableListMapperImpl
import br.com.lighttasks.data.mapper.basic_user.BasicUserEntityToRequestMapper
import br.com.lighttasks.data.mapper.basic_user.BasicUserResponseToEntityMapper
import br.com.lighttasks.data.mapper.task.TaskEntityToRequestMapper
import br.com.lighttasks.data.mapper.task.TaskResponseToEntityMapper
import br.com.lighttasks.data.mapper.team.TeamEntityToRequestMapper
import br.com.lighttasks.data.mapper.team.TeamResponseToEntityMapper
import br.com.lighttasks.data.mapper.user.UserEntityToRequestMapper
import br.com.lighttasks.data.mapper.user.UserResponseToEntityMapper
import org.koin.dsl.module

val mapperModules = module {

    single { UserEntityToRequestMapper() }

    single { UserResponseToEntityMapper() }

    single { TaskEntityToRequestMapper() }

    single { TaskResponseToEntityMapper() }

    single { BasicUserEntityToRequestMapper() }

    single {
        BasicUserResponseToEntityMapper(
            taskResponseToEntityMapper = NullableListMapperImpl(
                mapper = get<TaskResponseToEntityMapper>()
            )
        )
    }

    single {
        TeamResponseToEntityMapper(
            basicUserResponseToEntityMapper = NullableListMapperImpl(
                mapper = get<BasicUserResponseToEntityMapper>()
            )
        )
    }

    single {
        TeamEntityToRequestMapper(
            basicUserEntityToRequestMapper = NullableListMapperImpl(
                mapper = get<BasicUserEntityToRequestMapper>()
            )
        )
    }

}
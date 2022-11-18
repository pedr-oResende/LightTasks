package br.com.lighttasks.data.di

import br.com.lighttasks.commom.mapper.NullableListMapperImpl
import br.com.lighttasks.data.data_source.basic_user.BasicUserRemoteDataSourceImpl
import br.com.lighttasks.data.data_source.task.TaskRemoteDataSourceImpl
import br.com.lighttasks.data.data_source.team.TeamRemoteDataSourceImpl
import br.com.lighttasks.data.data_source.user.UserRemoteDataSourceImpl
import br.com.lighttasks.data.mapper.basic_user.BasicUserEntityToRequestMapper
import br.com.lighttasks.data.mapper.basic_user.BasicUserResponseToEntityMapper
import br.com.lighttasks.data.mapper.task.TaskEntityToRequestMapper
import br.com.lighttasks.data.mapper.task.TaskResponseToEntityMapper
import br.com.lighttasks.data.mapper.team.TeamEntityToRequestMapper
import br.com.lighttasks.data.mapper.team.TeamResponseToEntityMapper
import br.com.lighttasks.data.mapper.user.UserEntityToRequestMapper
import br.com.lighttasks.data.mapper.user.UserResponseToEntityMapper
import br.com.lighttasks.data.repository.BasicUserRepositoryImpl
import br.com.lighttasks.data.repository.TaskRepositoryImpl
import br.com.lighttasks.data.repository.TeamRepositoryImpl
import br.com.lighttasks.data.repository.UserRepositoryImpl
import br.com.lighttasks.domain.repository.BasicUserRepository
import br.com.lighttasks.domain.repository.TaskRepository
import br.com.lighttasks.domain.repository.TeamRepository
import br.com.lighttasks.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModules = module {

    single<UserRepository> {
        UserRepositoryImpl(
            userRemoteDataSource = UserRemoteDataSourceImpl(get()),
            userResponseToEntityMapper = get<UserResponseToEntityMapper>(),
            userEntityToRequestMapper = get<UserEntityToRequestMapper>(),
            basicUserResponseToEntityMapper = get<BasicUserResponseToEntityMapper>()
        )
    }

    single<BasicUserRepository> {
        BasicUserRepositoryImpl(
            basicUserRemoteDataSource = BasicUserRemoteDataSourceImpl(get()),
            basicUserResponseToEntityMapper = get<BasicUserResponseToEntityMapper>(),
            basicUserEntityToRequestMapper = get<BasicUserEntityToRequestMapper>(),
        )
    }

    single<TaskRepository> {
        TaskRepositoryImpl(
            taskRemoteDataSource = TaskRemoteDataSourceImpl(get()),
            taskResponseListToEntityMapper = NullableListMapperImpl(
                mapper = get<TaskResponseToEntityMapper>()
            ),
            taskResponseToEntityMapper = get<TaskResponseToEntityMapper>(),
            taskEntityToRequestMapper = get<TaskEntityToRequestMapper>()
        )
    }

    single<TeamRepository> {
        TeamRepositoryImpl(
            teamRemoteDataSource = TeamRemoteDataSourceImpl(get()),
            teamResponseListToEntityMapper = NullableListMapperImpl(
                mapper = get<TeamResponseToEntityMapper>()
            ),
            teamResponseToEntityMapper = get<TeamResponseToEntityMapper>(),
            teamEntityToRequestMapper = get<TeamEntityToRequestMapper>()
        )
    }

}
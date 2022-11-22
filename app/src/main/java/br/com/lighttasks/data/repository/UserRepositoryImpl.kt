package br.com.lighttasks.data.repository

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.data_source.user.UserRemoteDataSource
import br.com.lighttasks.data.mapper.basic_user.BasicUserResponseToEntityMapper
import br.com.lighttasks.data.mapper.user.UserEntityToRequestMapper
import br.com.lighttasks.data.mapper.user.UserResponseToEntityMapper
import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.data.remote.model.users.UserResponse
import br.com.lighttasks.data.remote.util.unsafeApiCall
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.User
import br.com.lighttasks.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userResponseToEntityMapper: Mapper<UserResponse, User>,
    private val userEntityToRequestMapper: Mapper<User, UserRequest>,
    private val basicUserResponseToEntityMapper: Mapper<BasicUserResponse, BasicUser>
) : UserRepository {

    override fun register(user: User): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.register(userEntityToRequestMapper.map(user))
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun logout(id: Long): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.logout(id)
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun login(user: User): Flow<BasicUser> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.login(userEntityToRequestMapper.map(user))
                val mappedResponse = basicUserResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteUser(id: Long): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.deleteUser(id)
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getUser(id: Long): Flow<User> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.getUser(id)
                val mappedResponse = userResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun editUser(id: Long, user: User): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = userRemoteDataSource.editUser(
                    id = id,
                    user = userEntityToRequestMapper.map(user)
                )
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }
}
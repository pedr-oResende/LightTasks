package br.com.lighttasks.data.data_source.user

import br.com.lighttasks.data.remote.model.basic_users.BasicUserResponse
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.data.remote.model.users.UserResponse

interface UserRemoteDataSource {

    suspend fun register(user: UserRequest)

    suspend fun login(user: UserRequest): BasicUserResponse

    suspend fun logout(id: Long)

    suspend fun deleteUser(id: Long)

    suspend fun getUser(id: Long): UserResponse

    suspend fun editUser(id: Long, user: UserRequest)

}
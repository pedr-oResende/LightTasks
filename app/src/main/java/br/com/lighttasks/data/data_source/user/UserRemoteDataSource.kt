package br.com.lighttasks.data.data_source.user

import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.data.remote.model.users.UserResponse

interface UserRemoteDataSource {

    fun register(user: UserRequest): Unit?

    fun login(user: UserRequest): BasicUserResponse

    fun logout(id: Long): Unit?

    fun deleteUser(id: Long): Unit?

    fun getUser(id: Long): UserResponse

    fun editUser(id: Long, user: UserRequest): Unit?

}
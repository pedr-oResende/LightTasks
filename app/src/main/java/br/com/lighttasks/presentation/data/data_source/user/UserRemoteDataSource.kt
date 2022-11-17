package br.com.lighttasks.presentation.data.data_source.user

import br.com.lighttasks.presentation.data.remote.dto.users.BasicUserResponse
import br.com.lighttasks.presentation.data.remote.dto.users.UserRequest
import br.com.lighttasks.presentation.data.remote.dto.users.UserResponse

interface UserRemoteDataSource {

    fun register(user: UserRequest): Unit?

    fun login(user: UserRequest): BasicUserResponse

    fun deleteUser(id: Long): Unit?

    fun getUser(id: Long): UserResponse

    fun editUser(id: Long, user: UserRequest): Unit?

}
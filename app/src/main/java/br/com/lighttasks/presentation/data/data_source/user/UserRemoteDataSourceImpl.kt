package br.com.lighttasks.presentation.data.data_source.user

import br.com.lighttasks.presentation.data.remote.dto.users.BasicUserResponse
import br.com.lighttasks.presentation.data.remote.dto.users.UserRequest
import br.com.lighttasks.presentation.data.remote.dto.users.UserResponse
import br.com.lighttasks.presentation.data.remote.service.UserService
import retrofit2.HttpException

class UserRemoteDataSourceImpl(
    private val service: UserService
) : UserRemoteDataSource {
    override fun register(user: UserRequest): Unit? {
        val response = service.register(user = user)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }

    override fun login(user: UserRequest): BasicUserResponse {
        val response = service.login(user = user)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun deleteUser(id: Long): Unit? {
        val response = service.deleteUserById(id = id)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }

    override fun getUser(id: Long): UserResponse {
        val response = service.getUserById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun editUser(id: Long, user: UserRequest): Unit? {
        val response = service.editUser(id = id, user = user)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }
}
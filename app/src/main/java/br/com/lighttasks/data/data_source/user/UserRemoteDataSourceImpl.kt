package br.com.lighttasks.data.data_source.user

import br.com.lighttasks.data.remote.model.basic_users.BasicUserResponse
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.data.remote.model.users.UserResponse
import br.com.lighttasks.data.remote.service.UserService
import retrofit2.HttpException

class UserRemoteDataSourceImpl(
    private val service: UserService
) : UserRemoteDataSource {
    override suspend fun register(user: UserRequest) {
        val response = service.register(user = user)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override suspend fun login(user: UserRequest): BasicUserResponse {
        val response = service.login(user = user)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override suspend fun logout(id: Long) {
        val response = service.logout(userId = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override suspend fun deleteUser(id: Long) {
        val response = service.deleteUserById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override suspend fun getUser(id: Long): UserResponse {
        val response = service.getUserById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override suspend fun editUser(id: Long, user: UserRequest) {
        val response = service.editUser(id = id, user = user)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }
}
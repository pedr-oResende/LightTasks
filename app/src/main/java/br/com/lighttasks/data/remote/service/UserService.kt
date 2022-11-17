package br.com.lighttasks.data.remote.service

import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.data.remote.model.users.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("/users/register")
    fun register(@Body user: UserRequest): Response<Unit>

    @POST("/users/login")
    fun login(@Body user: UserRequest): Response<BasicUserResponse>

    @POST("/users/logout/{id}")
    fun logout(@Path("id") userId: Long): Response<Unit>

    @DELETE("/users/{id}")
    fun deleteUserById(@Path("id") id: Long): Response<Unit>

    @GET("user/{id}")
    fun getUserById(@Path("id") id: Long): Response<UserResponse>

    @PUT("users/{id}")
    fun editUser(
        @Path("id") id: Long,
        @Body user: UserRequest
    ): Response<Unit>

}
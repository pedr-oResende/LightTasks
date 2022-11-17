package br.com.lighttasks.presentation.data.remote.service

import br.com.lighttasks.presentation.data.remote.dto.users.BasicUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BasicUserService {

    @GET("/basic_users/{id}")
    fun getBasicUserById(@Path("id") id: Long): Response<BasicUserResponse>

    @PUT("/basic_users/{memberId}/add_to_team/{teamId}")
    fun addTeamMember(
        @Path("memberId") memberId: Long?,
        @Path("teamId") teamId: Long?,
    ): Response<Unit>

    @PUT("/basic_users/{memberId}/remove_of_team/{teamId}")
    fun removeTeamMember(
        @Path("memberId") memberId: Long?,
        @Path("teamId") teamId: Long?,
    ): Response<Unit>

}
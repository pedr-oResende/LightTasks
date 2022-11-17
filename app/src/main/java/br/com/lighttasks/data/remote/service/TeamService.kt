package br.com.lighttasks.data.remote.service

import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.teams.TeamResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TeamService {

    @GET("/teams/{userId}")
    fun getTeamsByUser(@Path("userId") userId: Long): Response<List<TeamResponse>>

    @GET("/teams/{id}")
    fun getTeamsById(@Path("id") id: Long): Response<TeamResponse>

    @POST("/teams")
    fun createTeam(@Body team: TeamRequest): Response<TeamResponse>

    @PUT("/teams/{id}")
    fun editTeam(@Path("id") id: Long, @Body team: TeamRequest): Response<TeamResponse>

    @DELETE("/teams/{id}")
    fun deleteTeam(@Path("id") id: Long): Response<Unit>
}
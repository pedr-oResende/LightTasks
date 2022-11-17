package br.com.lighttasks.data.data_source.team

import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.teams.TeamResponse
import br.com.lighttasks.data.remote.service.TeamService
import retrofit2.HttpException

class TeamRemoteDataSourceImpl(
    private val service: TeamService
) : br.com.lighttasks.data.data_source.team.TeamRemoteDataSource {
    override fun getTeamsByUser(id: Long): List<TeamResponse> {
        val response = service.getTeamsByUser(userId = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun getTeamsById(id: Long): TeamResponse {
        val response = service.getTeamsById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun createTeam(team: TeamRequest): TeamResponse {
        val response = service.createTeam(team = team)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun editTeam(id: Long, team: TeamRequest): TeamResponse {
        val response = service.editTeam(id = id, team = team)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun deleteTeam(id: Long): Unit? {
        val response = service.deleteTeam(id = id)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }

}
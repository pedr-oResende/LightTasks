package br.com.lighttasks.data.data_source.team

import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.teams.TeamResponse

interface TeamRemoteDataSource {

    fun getTeamsByUser(id: Long): List<TeamResponse>

    fun getTeamById(id: Long): TeamResponse

    fun createTeam(team: TeamRequest): TeamResponse

    fun editTeam(id: Long, team: TeamRequest): TeamResponse

    fun deleteTeam(id: Long): Unit?

}
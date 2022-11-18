package br.com.lighttasks.data.repository

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.data.data_source.team.TeamRemoteDataSource
import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.teams.TeamResponse
import br.com.lighttasks.data.remote.util.unsafeApiCall
import br.com.lighttasks.domain.model.Team
import br.com.lighttasks.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepositoryImpl(
    private val teamRemoteDataSource: TeamRemoteDataSource,
    private val teamResponseListToEntityMapper: NullableListMapper<TeamResponse, Team>,
    private val teamResponseToEntityMapper: Mapper<TeamResponse, Team>,
    private val teamEntityToRequestMapper: Mapper<Team, TeamRequest>,
) : TeamRepository {
    override fun getTeamsByUser(id: Long): Flow<List<Team>> {
        return flow {
            unsafeApiCall {
                val response = teamRemoteDataSource.getTeamsByUser(id = id)
                val mappedResponse = teamResponseListToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun getTeamById(id: Long): Flow<Team> {
        return flow {
            unsafeApiCall {
                val response = teamRemoteDataSource.getTeamById(id = id)
                val mappedResponse = teamResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun createTeam(team: Team): Flow<Team> {
        return flow {
            unsafeApiCall {
                val response = teamRemoteDataSource.createTeam(
                    team = teamEntityToRequestMapper.map(team)
                )
                val mappedResponse = teamResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun editTeam(id: Long, team: Team): Flow<Team> {
        return flow {
            unsafeApiCall {
                val response = teamRemoteDataSource.editTeam(
                    id = id,
                    team = teamEntityToRequestMapper.map(team)
                )
                val mappedResponse = teamResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun deleteTeam(id: Long): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = teamRemoteDataSource.deleteTeam(id = id)
                emit(response)
            }
        }
    }
}
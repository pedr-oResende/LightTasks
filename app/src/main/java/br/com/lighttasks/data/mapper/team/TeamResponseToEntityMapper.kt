package br.com.lighttasks.data.mapper.team

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.data.remote.model.teams.TeamResponse
import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Team

class TeamResponseToEntityMapper(
    private val basicUserResponseToEntityMapper: NullableListMapper<BasicUserResponse, BasicUser>
) : Mapper<TeamResponse, Team> {
    override fun map(input: TeamResponse) =
        with(input) {
            Team(
                id = id,
                name = name,
                createdAt = createdAt,
                leaderId = leaderId,
                members = basicUserResponseToEntityMapper.map(members)
            )
        }
}
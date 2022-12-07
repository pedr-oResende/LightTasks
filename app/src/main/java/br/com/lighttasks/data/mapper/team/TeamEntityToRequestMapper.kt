package br.com.lighttasks.data.mapper.team

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.basic_users.BasicUserRequest
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Team

class TeamEntityToRequestMapper(
    private val basicUserEntityToRequestMapper: NullableListMapper<BasicUser, BasicUserRequest>
) : Mapper<Team, TeamRequest> {
    override fun map(input: Team) =
        with(input) {
            TeamRequest(
                name = name,
                leaderId = leaderId,
                members = basicUserEntityToRequestMapper.map(members)
            )
        }
}
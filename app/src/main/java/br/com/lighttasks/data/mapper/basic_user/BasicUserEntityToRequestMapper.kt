package br.com.lighttasks.data.mapper.basic_user

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.remote.model.basic_users.BasicUserRequest
import br.com.lighttasks.domain.model.BasicUser

class BasicUserEntityToRequestMapper : Mapper<BasicUser, BasicUserRequest> {
    override fun map(input: BasicUser) =
        with(input) {
            BasicUserRequest(
                username = username,
                fullName = fullName
            )
        }
}
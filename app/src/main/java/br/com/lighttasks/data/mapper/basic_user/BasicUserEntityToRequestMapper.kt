package br.com.lighttasks.data.mapper.basic_user

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.data.remote.model.users.BasicUserRequest
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task

class BasicUserEntityToRequestMapper : Mapper<BasicUser, BasicUserRequest> {
    override fun map(input: BasicUser) =
        with(input) {
            BasicUserRequest(
                username = username
            )
        }
}
package br.com.lighttasks.data.mapper.user

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.remote.model.users.UserRequest
import br.com.lighttasks.domain.model.User

class UserEntityToRequestMapper : Mapper<User, UserRequest> {
    override fun map(input: User) =
        with(input) {
            UserRequest(
                username = username,
                password = password
            )
        }
}
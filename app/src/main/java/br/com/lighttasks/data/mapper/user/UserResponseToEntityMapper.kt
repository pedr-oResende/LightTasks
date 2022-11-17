package br.com.lighttasks.data.mapper.user

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.remote.model.users.UserResponse
import br.com.lighttasks.domain.model.User

class UserResponseToEntityMapper : Mapper<UserResponse, User> {
    override fun map(input: UserResponse) =
        with(input) {
            User(
                id = id,
                username = username,
                password = password
            )
        }
}
package br.com.lighttasks.data.mapper.basic_user

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task

class BasicUserResponseToEntityMapper(
    private val taskResponseToEntityMapper: NullableListMapper<TaskResponse, Task>
) : Mapper<BasicUserResponse, BasicUser> {
    override fun map(input: BasicUserResponse) =
        with(input) {
            BasicUser(
                id = id,
                username = username,
                teamsId = teamsId,
                tasks = taskResponseToEntityMapper.map(tasks)
            )
        }
}
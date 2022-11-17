package br.com.lighttasks.data.mapper.task

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.domain.model.Task

class TaskEntityToRequestMapper : Mapper<Task, TaskRequest> {
    override fun map(input: Task) =
        with(input) {
            TaskRequest(
                name = name,
                deadline = deadline,
                isDone = isDone,
                responsibleId = responsibleId,
                description = description,
                teamId = teamId
            )
        }
}
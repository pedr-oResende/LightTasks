package br.com.lighttasks.data.mapper.task

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task

class TaskResponseToEntityMapper : Mapper<TaskResponse, Task> {
    override fun map(input: TaskResponse) =
        with(input) {
            Task(
                id = id,
                name = name,
                deadline = deadline,
                createdAt = createdAt,
                isDone = isDone,
                responsibleId = responsibleId,
                teamId = teamId,
                description = description,
                priority = Priority.getPriority(deadline)
            )
        }
}
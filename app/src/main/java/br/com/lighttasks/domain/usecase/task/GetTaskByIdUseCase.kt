package br.com.lighttasks.domain.usecase.task

import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTaskByIdUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Long): Flow<Task> {
        return repository.getTaskById(id = id)
    }
}
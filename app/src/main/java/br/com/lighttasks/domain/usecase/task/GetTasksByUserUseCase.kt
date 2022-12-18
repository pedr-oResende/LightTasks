package br.com.lighttasks.domain.usecase.task

import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksByUserUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Long?): Flow<List<Task>> {
        return repository.getTasksByUser(id = id)
    }
}
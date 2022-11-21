package br.com.lighttasks.domain.usecase.task

import br.com.lighttasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class DeleteTaskUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Long): Flow<Unit?> {
        return repository.deleteTask(id = id)
    }
}
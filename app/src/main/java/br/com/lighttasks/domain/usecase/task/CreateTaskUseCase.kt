package br.com.lighttasks.domain.usecase.task

import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(task: Task): Flow<Task> {
        return repository.createTask(task = task)
    }
}
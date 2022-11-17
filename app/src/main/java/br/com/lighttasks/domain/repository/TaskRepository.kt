package br.com.lighttasks.domain.repository

import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import br.com.lighttasks.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasksByUser(id: Long): Flow<List<TaskResponse>>

    fun getTasksById(id: Long): Flow<Task>

    fun createTask(task: TaskRequest): Flow<Task>

    fun editTask(id: Long, task: TaskRequest): Flow<Task>

    fun deleteTask(id: Long): Flow<Unit?>
}
package br.com.lighttasks.domain.repository

import br.com.lighttasks.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasksByUser(id: Long): Flow<List<Task>>

    fun getTaskById(id: Long): Flow<Task>

    fun createTask(task: Task): Flow<Task>

    fun editTask(task: Task): Flow<Task>

    fun deleteTask(id: Long): Flow<Unit?>
}
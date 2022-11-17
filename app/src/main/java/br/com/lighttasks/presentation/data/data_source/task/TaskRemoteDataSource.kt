package br.com.lighttasks.presentation.data.data_source.task

import br.com.lighttasks.presentation.data.remote.dto.tasks.TaskRequest
import br.com.lighttasks.presentation.data.remote.dto.tasks.TaskResponse

interface TaskRemoteDataSource {

    fun getTasksByUser(id: Long): List<TaskResponse>

    fun getTasksById(id: Long): TaskResponse

    fun createTask(task: TaskRequest): TaskResponse

    fun editTask(id: Long, task: TaskRequest): TaskResponse

    fun deleteTask(id: Long): Unit?

}
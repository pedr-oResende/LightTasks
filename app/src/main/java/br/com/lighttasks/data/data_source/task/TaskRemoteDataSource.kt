package br.com.lighttasks.data.data_source.task

import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.data.remote.model.tasks.TaskResponse

interface TaskRemoteDataSource {

    fun getTasksByUser(id: Long): List<TaskResponse>

    fun getTaskById(id: Long): TaskResponse

    fun createTask(task: TaskRequest): TaskResponse

    fun editTask(id: Long, task: TaskRequest): TaskResponse

    fun deleteTask(id: Long): Unit?

}
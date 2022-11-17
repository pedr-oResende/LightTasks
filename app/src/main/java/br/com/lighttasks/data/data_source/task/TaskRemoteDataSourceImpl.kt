package br.com.lighttasks.data.data_source.task

import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import br.com.lighttasks.data.remote.service.TaskService
import retrofit2.HttpException

class TaskRemoteDataSourceImpl(
    private val service: TaskService
) : br.com.lighttasks.data.data_source.task.TaskRemoteDataSource {
    override fun getTasksByUser(id: Long): List<TaskResponse> {
        val response = service.getTasksByUser(userId = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun getTasksById(id: Long): TaskResponse {
        val response = service.getTasksById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun createTask(task: TaskRequest): TaskResponse {
        val response = service.createTask(task = task)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun editTask(id: Long, task: TaskRequest): TaskResponse {
        val response = service.editTask(id = id, task = task)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun deleteTask(id: Long): Unit? {
        val response = service.deleteTask(id = id)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }
}
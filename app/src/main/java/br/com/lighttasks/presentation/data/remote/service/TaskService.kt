package br.com.lighttasks.presentation.data.remote.service

import br.com.lighttasks.presentation.data.remote.dto.tasks.TaskRequest
import br.com.lighttasks.presentation.data.remote.dto.tasks.TaskResponse
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @GET("/tasks/{userId}")
    fun getTasksByUser(@Path("userId") userId: Long): Response<List<TaskResponse>>

    @GET("/tasks/{id}")
    fun getTasksById(@Path("id") id: Long): Response<TaskResponse>

    @POST("/tasks")
    fun createTask(@Body task: TaskRequest): Response<TaskResponse>

    @PUT("/tasks/{id}")
    fun editTask(
        @Path("id") id: Long,
        @Body task: TaskRequest
    ): Response<TaskResponse>

    @DELETE("/tasks/{id}")
    fun deleteTask(@Path("id") id: Long): Response<Unit>

}
package br.com.lighttasks.data.repository

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.commom.mapper.NullableListMapper
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.data.data_source.task.TaskRemoteDataSource
import br.com.lighttasks.data.remote.model.tasks.TaskRequest
import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import br.com.lighttasks.data.remote.util.apiCall
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.repository.TaskRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepositoryImpl(
    private val taskRemoteDataSource: TaskRemoteDataSource,
    private val taskResponseListToEntityMapper: NullableListMapper<TaskResponse, Task>,
    private val taskEntityToRequestMapper: Mapper<Task, TaskRequest>,
    private val taskResponseToEntityMapper: Mapper<TaskResponse, Task>
) : TaskRepository {
    override fun getTasksByUser(id: Long?): Flow<List<Task>> {
        return flow {
            apiCall {
//                val response = taskRemoteDataSource.getTasksByUser(id = id)
//                val mappedResponse = taskResponseListToEntityMapper.map(response)
//                emit(mappedResponse)
                val user = PreferencesWrapper.getInstance()?.getUser()
                delay(1000L)
                emit(user?.tasks.orEmpty())
            }
        }
    }

    override fun getTaskById(id: Long): Flow<Task> {
        return flow {
            apiCall {
                val response = taskRemoteDataSource.getTaskById(id = id)
                val mappedResponse = taskResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun createTask(task: Task): Flow<Task> {
        return flow {
            apiCall {
                val response = taskRemoteDataSource.createTask(
                    task = taskEntityToRequestMapper.map(task)
                )
                val mappedResponse = taskResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

    override fun editTask(task: Task): Flow<Task> {
        return flow {
            apiCall {
//                val response = taskRemoteDataSource.editTask(
//                    id = task.id!!,
//                    task = taskEntityToRequestMapper.map(task)
//                )
//                val mappedResponse = taskResponseToEntityMapper.map(response)
//                emit(mappedResponse)
                delay(1000L)
                emit(task)
            }
        }
    }

    override fun deleteTask(id: Long): Flow<Unit?> {
        return flow {
            apiCall {
                val response = taskRemoteDataSource.deleteTask(id = id)
                emit(response)
            }
        }
    }
}
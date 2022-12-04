package br.com.lighttasks.domain.model

data class BasicUser(
    val id: Long?,
    val username: String?,
    val fullName: String?,
    val tasks: List<Task>?,
    val teamsId: List<Long>?
)
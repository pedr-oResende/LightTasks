package br.com.lighttasks.domain.model

data class Task(
    val id: Long?,
    val name: String?,
    val createdAt: String?,
    val deadline: String?,
    val description: String?,
    val teamId: Long?,
    val responsibleId: Long?,
    val isDone: Boolean?
)
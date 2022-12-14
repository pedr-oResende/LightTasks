package br.com.lighttasks.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Long?,
    val name: String?,
    val createdAt: String?,
    val deadline: String?,
    val description: String?,
    val teamId: Long?,
    val responsibleId: Long?,
    val isDone: Boolean?,
    val priority: Priority
) : Parcelable

val tasks = listOf(
    Task(
        id = 1,
        name = "Varrer a casa",
        description = "Varrer antes das 18h",
        teamId = null,
        responsibleId = 1,
        isDone = false,
        createdAt = "2022-11-22",
        deadline = "2023-01-04",
        priority = Priority.getPriority("2023-01-04")
    ),
    Task(
        id = 2,
        name = "Mercado",
        description = "Comprar macarrão e salsicha",
        teamId = null,
        responsibleId = 1,
        isDone = false,
        createdAt = "2022-11-22",
        deadline = "2023-01-03",
        priority = Priority.getPriority("2023-01-03")
    ),
    Task(
        id = 3,
        name = "Visão computacional",
        description = "Fazer o trabalho do gian",
        teamId = null,
        responsibleId = 1,
        isDone = false,
        createdAt = "2022-11-22",
        deadline = "2023-01-30",
        priority = Priority.getPriority("2023-01-30")
    )
)
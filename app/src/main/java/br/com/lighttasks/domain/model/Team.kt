package br.com.lighttasks.domain.model

data class Team(
    val id: Long?,
    val name: String?,
    val createdAt: String?,
    val leaderId: Long?,
    val members: List<BasicUser>?
)
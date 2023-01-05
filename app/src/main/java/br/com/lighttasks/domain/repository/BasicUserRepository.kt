package br.com.lighttasks.domain.repository

import br.com.lighttasks.domain.model.BasicUser
import kotlinx.coroutines.flow.Flow

interface BasicUserRepository {

    fun getBasicUserById(id: Long): Flow<BasicUser>

    fun editBasicUser(basicUser: BasicUser): Flow<BasicUser>

    fun addTeamMember(memberId: Long, teamId: Long): Flow<Unit?>

    fun removeTeamMember(memberId: Long?, teamId: Long?): Flow<Unit?>
}
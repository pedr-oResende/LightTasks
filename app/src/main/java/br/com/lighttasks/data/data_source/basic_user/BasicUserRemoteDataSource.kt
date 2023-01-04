package br.com.lighttasks.data.data_source.basic_user

import br.com.lighttasks.data.remote.model.basic_users.BasicUserRequest
import br.com.lighttasks.data.remote.model.basic_users.BasicUserResponse

interface BasicUserRemoteDataSource {

    fun getBasicUserById(id: Long?): BasicUserResponse

    fun editBasicUser(id: Long, basicUser: BasicUserRequest): BasicUserResponse

    fun addTeamMember(memberId: Long, teamId: Long): Unit?

    fun removeTeamMember(memberId: Long?, teamId: Long?): Unit?

}
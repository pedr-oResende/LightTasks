package br.com.lighttasks.presentation.data.data_source.basic_user

import br.com.lighttasks.presentation.data.remote.dto.users.BasicUserResponse

interface BasicUserRemoteDataSource {

    fun getBasicUserById(id: Long): BasicUserResponse

    fun addTeamMember(memberId: Long, teamId: Long, ): Unit?

    fun removeTeamMember(memberId: Long?, teamId: Long?, ): Unit?

}
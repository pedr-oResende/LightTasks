package br.com.lighttasks.data.data_source.basic_user

import br.com.lighttasks.data.remote.model.users.BasicUserRequest
import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.data.remote.service.BasicUserService
import br.com.lighttasks.domain.model.BasicUser
import retrofit2.HttpException

class BasicUserRemoteDataSourceImpl(
    private val service: BasicUserService
) : BasicUserRemoteDataSource {

    override fun getBasicUserById(id: Long): BasicUserResponse {
        val response = service.getBasicUserById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }


    override fun editBasicUser(basicUser: BasicUserRequest): BasicUserResponse {
        val response = service.editBasicUser(basicUser = basicUser)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun addTeamMember(memberId: Long, teamId: Long): Unit? {
        val response = service.addTeamMember(memberId = memberId, teamId = teamId)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }

    override fun removeTeamMember(memberId: Long?, teamId: Long?): Unit? {
        val response = service.removeTeamMember(memberId = memberId, teamId = teamId)
        if (response.isSuccessful)
            return response.body()
        throw HttpException(response)
    }
}
package br.com.lighttasks.data.data_source.basic_user

import br.com.lighttasks.data.remote.model.basic_users.BasicUserRequest
import br.com.lighttasks.data.remote.model.basic_users.BasicUserResponse
import br.com.lighttasks.data.remote.service.BasicUserService
import retrofit2.HttpException

class BasicUserRemoteDataSourceImpl(
    private val service: BasicUserService
) : BasicUserRemoteDataSource {

    override fun getBasicUserById(id: Long?): BasicUserResponse {
        val response = service.getBasicUserById(id = id)
        if (response.isSuccessful)
            return response.body()!!
        throw HttpException(response)
    }

    override fun editBasicUser(id: Long, basicUser: BasicUserRequest): BasicUserResponse {
        val response = service.editBasicUser(id = id, basicUser = basicUser)
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
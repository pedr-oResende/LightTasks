package br.com.lighttasks.data.repository

import br.com.lighttasks.commom.mapper.Mapper
import br.com.lighttasks.data.data_source.basic_user.BasicUserRemoteDataSource
import br.com.lighttasks.data.remote.model.users.BasicUserRequest
import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import br.com.lighttasks.data.remote.util.unsafeApiCall
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.repository.BasicUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasicUserRepositoryImpl(
    private val basicUserRemoteDataSource: BasicUserRemoteDataSource,
    private val basicUserEntityToRequestMapper: Mapper<BasicUser, BasicUserRequest>,
    private val basicUserResponseToEntityMapper: Mapper<BasicUserResponse, BasicUser>,
) : BasicUserRepository {

    override fun getBasicUserById(id: Long): Flow<BasicUser> {
        return flow {
            unsafeApiCall {
                val response = basicUserRemoteDataSource.getBasicUserById(id)
                val responseMapped = basicUserResponseToEntityMapper.map(response)
                emit(responseMapped)
            }
        }
    }

    override fun editBasicUser(basicUser: BasicUser): Flow<BasicUser> {
        return flow {
            val response = basicUserRemoteDataSource.editBasicUser(basicUserEntityToRequestMapper.map(basicUser))
            val mappedResponse = basicUserResponseToEntityMapper.map(response)
            emit(mappedResponse)
        }
    }

    override fun addTeamMember(memberId: Long, teamId: Long): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = basicUserRemoteDataSource.addTeamMember(memberId, teamId)
                emit(response)
            }
        }
    }

    override fun removeTeamMember(memberId: Long?, teamId: Long?): Flow<Unit?> {
        return flow {
            unsafeApiCall {
                val response = basicUserRemoteDataSource.removeTeamMember(memberId, teamId)
                emit(response)
            }
        }
    }

}
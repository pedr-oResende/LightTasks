package br.com.lighttasks.domain.repository

import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun register(user: User): Flow<Unit?>

    fun logout(id: Long): Flow<Unit?>

    fun login(user: User): Flow<BasicUser?>

    fun deleteUser(id: Long): Flow<Unit?>

    fun getUser(id: Long): Flow<User?>

    fun editUser(id: Long, user: User): Flow<Unit?>

}
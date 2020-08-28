package wottrich.github.io.androidworkshop_crud.data.repository

import kotlinx.coroutines.flow.Flow
import wottrich.github.io.androidworkshop_crud.data.dao.UserDao
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

interface UserRepository {
    val users: Flow<List<User>>

    suspend fun insertUser (user: User): Long

//    suspend fun insertUser (users: List<User>)
}

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override val users: Flow<List<User>>
        get() = dao.getAll()

    override suspend fun insertUser(user: User): Long {
        return dao.insert(user)
    }

//    override suspend fun insertUser(users: List<User>) {
//        return dao.insert(users)
//    }

}
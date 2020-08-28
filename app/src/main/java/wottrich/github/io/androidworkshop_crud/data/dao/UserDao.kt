package wottrich.github.io.androidworkshop_crud.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(users: List<User>)

}
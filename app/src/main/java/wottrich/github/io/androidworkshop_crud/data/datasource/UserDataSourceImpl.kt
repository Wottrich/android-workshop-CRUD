package wottrich.github.io.androidworkshop_crud.data.datasource

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wottrich.github.io.androidworkshop_crud.archive.callRequest
import wottrich.github.io.androidworkshop_crud.data.api.INetworkAPI
import wottrich.github.io.androidworkshop_crud.data.network.RetrofitInstance
import wottrich.github.io.androidworkshop_crud.data.resource.NetworkBoundResource
import wottrich.github.io.androidworkshop_crud.data.resource.Resource
import wottrich.github.io.androidworkshop_crud.model.User
import java.math.BigInteger

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

interface UserDataSource {
    suspend fun loadUsers (): Flow<Resource<List<User>>>
    suspend fun createUser (name: String): Flow<Resource<List<User>>>
    suspend fun updateUser (id: BigInteger, newName: String): Flow<Resource<List<User>>>
    suspend fun deleteUser (user: User): Flow<Resource<List<User>>>
}

class UserDataSourceImpl (
    private val api: INetworkAPI = RetrofitInstance.api
): UserDataSource {

    override suspend fun loadUsers (): Flow<Resource<List<User>>> {
        return flow {
            NetworkBoundResource<List<User>, List<User>>(
                collector = this,
                processResponse = { it },
                call = { api.getUsers() }
            ).build()
        }
    }

    override suspend fun createUser(name: String): Flow<Resource<List<User>>> {
        val body = hashMapOf(
            "name" to name
        )
        return flow {
            NetworkBoundResource<List<User>, List<User>>(
                collector = this,
                processResponse = { it },
                call = { api.createUser(body) }
            ).build()
        }
    }

    override suspend fun updateUser(id: BigInteger, newName: String): Flow<Resource<List<User>>> {
        val body = hashMapOf<String, Any>(
            "id" to id,
            "name" to newName
        )
        return flow {
            NetworkBoundResource<List<User>, List<User>>(
                collector = this,
                processResponse = { it },
                call = { api.updateUser(body) }
            ).build()
        }
    }

    override suspend fun deleteUser(user: User): Flow<Resource<List<User>>> {
        return flow {
            NetworkBoundResource<List<User>, List<User>>(
                collector = this,
                processResponse = { it },
                call = { api.deleteUser(user.id.toString()) }
            ).build()
        }
    }

}
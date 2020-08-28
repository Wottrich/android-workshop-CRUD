package wottrich.github.io.androidworkshop_crud.data.datasource

import androidx.lifecycle.LiveData
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
    suspend fun loadUsers (): LiveData<Resource<List<User>>>
    suspend fun createUser (name: String): LiveData<Resource<List<User>>>
    suspend fun updateUser (id: BigInteger, newName: String): LiveData<Resource<List<User>>>
    suspend fun deleteUser (user: User): LiveData<Resource<List<User>>>
}

class UserDataSourceImpl (
    private val api: INetworkAPI = RetrofitInstance.api
): UserDataSource {

    override suspend fun loadUsers (): LiveData<Resource<List<User>>> {
        return NetworkBoundResource<List<User>, List<User>>(
            processResponse = { it },
            call = { api.getUsers() }
        ).build().asLiveData()
    }

    override suspend fun createUser(name: String): LiveData<Resource<List<User>>> {
        val body = hashMapOf(
            "name" to name
        )
        return NetworkBoundResource<List<User>, List<User>>(
            processResponse = { it },
            call = { api.createUser(body) }
        ).build().asLiveData()
    }

    override suspend fun updateUser(id: BigInteger, newName: String): LiveData<Resource<List<User>>> {
        val body = hashMapOf<String, Any>(
            "id" to id,
            "name" to newName
        )
        return NetworkBoundResource<List<User>, List<User>>(
            processResponse = { it },
            call = { api.updateUser(body) }
        ).build().asLiveData()
    }

    override suspend fun deleteUser(user: User): LiveData<Resource<List<User>>> {
        return NetworkBoundResource<List<User>, List<User>>(
            processResponse = { it },
            call = { api.deleteUser(user.id.toString()) }
        ).build().asLiveData()
    }

}
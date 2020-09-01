package wottrich.github.io.androidworkshop_crud.data.datasource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wottrich.github.io.androidworkshop_crud.archive.callRequest
import wottrich.github.io.androidworkshop_crud.data.api.INetworkAPI
import wottrich.github.io.androidworkshop_crud.data.network.RetrofitInstance
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

typealias RequestCallback = (users: List<User>?, messageError: String?) -> Unit

interface UserDataSource {
    fun loadUsers (callback: RequestCallback)
    fun createUser (name: String, callback: RequestCallback)
    fun updateUser (id: BigInteger, newName: String, callback: RequestCallback)
    fun deleteUser (user: User, callback: RequestCallback)
}

class UserDataSourceImpl (
    private val api: INetworkAPI = RetrofitInstance.api
): UserDataSource {

    override fun loadUsers (callback: (users: List<User>?, messageError: String?) -> Unit) {
        api.getUsers().callRequest(callback)
    }

    override fun createUser (name: String, callback: (users: List<User>?, messageError: String?) -> Unit) {
        val body = hashMapOf(
            "name" to name
        )
        api.createUser(body).callRequest(callback)
    }

    override fun updateUser (id: BigInteger, newName: String, callback: (users: List<User>?, messageError: String?) -> Unit) {
        val body = hashMapOf<String, Any>(
            "id" to id,
            "name" to newName
        )
        api.updateUser(body).callRequest(callback)
    }

    override fun deleteUser (user: User, callback: (users: List<User>?, messageError: String?) -> Unit) {
        api.deleteUser(user.id.toString()).callRequest(callback)
    }

}
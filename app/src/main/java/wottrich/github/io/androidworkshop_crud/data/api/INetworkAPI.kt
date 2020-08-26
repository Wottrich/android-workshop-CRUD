package wottrich.github.io.androidworkshop_crud.data.api

import retrofit2.Call
import retrofit2.http.*
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
interface INetworkAPI {

    @GET("users")
    fun getUsers () : Call<List<User>>

    @POST("users/create")
    fun createUser(@Body body: HashMap<String, String>) : Call<List<User>>

    @PUT("users/update")
    fun updateUser(@Body body: HashMap<String, Any>) : Call<List<User>>

    @DELETE("users/delete/{id}")
    fun deleteUser(@Path("id") id: String) : Call<List<User>>

}
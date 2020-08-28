package wottrich.github.io.androidworkshop_crud.data.api

import retrofit2.http.*
import wottrich.github.io.androidworkshop_crud.util.resource.ApiResponse
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
    suspend fun getUsers () : ApiResponse<List<User>>

    @POST("users/create")
    suspend fun createUser(@Body body: HashMap<String, String>) : ApiResponse<List<User>>

    @PUT("users/update")
    suspend fun updateUser(@Body body: HashMap<String, Any>) : ApiResponse<List<User>>

    @DELETE("users/delete/{id}")
    suspend fun deleteUser(@Path("id") id: String) : ApiResponse<List<User>>

}
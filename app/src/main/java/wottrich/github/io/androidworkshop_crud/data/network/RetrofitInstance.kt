package wottrich.github.io.androidworkshop_crud.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wottrich.github.io.androidworkshop_crud.data.api.INetworkAPI

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
object RetrofitInstance {

    private const val baseUrl = "http://192.168.15.75:5000/"

    val api: INetworkAPI
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(Client.clientHTTP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(INetworkAPI::class.java)


}
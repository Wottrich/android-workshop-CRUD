package wottrich.github.io.androidworkshop_crud.data.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
object Client {

    val clientHTTP: OkHttpClient
        get() = OkHttpClient.Builder().apply {
            addLoggingInterceptor()
        }.build()

    private fun OkHttpClient.Builder.addLoggingInterceptor() : OkHttpClient.Builder {
        return addInterceptor (
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

}
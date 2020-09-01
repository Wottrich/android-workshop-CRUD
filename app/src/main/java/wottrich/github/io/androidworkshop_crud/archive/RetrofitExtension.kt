package wottrich.github.io.androidworkshop_crud.archive

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

fun <T> Call<T>.callRequest(callback: (T?, String?) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful && response.code() == 200) {
                callback(response.body(), null)
            } else {
                callback(null, response.message())
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback(null, t.message)
        }

    })
}
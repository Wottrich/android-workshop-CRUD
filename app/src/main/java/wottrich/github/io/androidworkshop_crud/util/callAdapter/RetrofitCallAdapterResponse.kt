package wottrich.github.io.androidworkshop_crud.util.callAdapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wottrich.github.io.androidworkshop_crud.data.resource.ApiResponse
import java.lang.UnsupportedOperationException

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

class RetrofitCallAdapterResponse <R>(
    private val call: Call<R>
): Call<ApiResponse<R>> {

    override fun enqueue(callback: Callback<ApiResponse<R>>) {
        return call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@RetrofitCallAdapterResponse,
                    Response.success(ApiResponse.create(response))
                )
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                callback.onResponse(
                    this@RetrofitCallAdapterResponse,
                    Response.success(ApiResponse.create(t))
                )
            }

        })
    }

    override fun clone(): Call<ApiResponse<R>> = RetrofitCallAdapterResponse(call)

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    override fun execute(): Response<ApiResponse<R>> {
        throw UnsupportedOperationException("CallAdapterResponse doesn't support execute")
    }

}
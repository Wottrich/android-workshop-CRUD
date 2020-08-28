package wottrich.github.io.androidworkshop_crud.util.callAdapter

import retrofit2.Call
import retrofit2.CallAdapter
import wottrich.github.io.androidworkshop_crud.util.resource.ApiResponse
import java.lang.reflect.Type

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

class RetrofitCallAdapter <R> (
    private val responseType: Type
): CallAdapter<R, Call<ApiResponse<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<ApiResponse<R>> {
        return RetrofitCallAdapterResponse(call)
    }

}
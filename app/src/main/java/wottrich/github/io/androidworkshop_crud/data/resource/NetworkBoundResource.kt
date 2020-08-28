package wottrich.github.io.androidworkshop_crud.data.resource

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.coroutineContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

class NetworkBoundResource<ResultType, RequestType>(
    private val collector: FlowCollector<Resource<ResultType>>,
    private val processResponse: (response: RequestType) -> ResultType,
    private val call: suspend () -> ApiResponse<RequestType>
) {

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        collector.emit(Resource.loading())
        fetchFromNetwork()
        return this
    }

    private suspend fun fetchFromNetwork() {
        return when (val result = call()) {
            is ApiSuccessResponse -> {
                val process = processResponse(result.body)
                collector.emit(Resource.success(process))
            }
            is ApiEmptyResponse -> {
                collector.emit(Resource.success(null))
            }
            is ApiErrorResponse -> {
                collector.emit(Resource.error(result.error))
            }
        }
    }

}
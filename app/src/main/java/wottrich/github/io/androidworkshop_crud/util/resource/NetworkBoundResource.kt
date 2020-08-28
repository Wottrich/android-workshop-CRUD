package wottrich.github.io.androidworkshop_crud.util.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect

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
    private val saveCallResult: (suspend (item: ResultType) -> Unit)? = null,
    private val shouldFetch: (data: ResultType?) -> Boolean = {true},
    private val loadFromDb: (() -> Flow<ResultType>)? = null,
    private val processResponse: (response: RequestType) -> ResultType,
    private val call: suspend () -> ApiResponse<RequestType>
) {

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        collector.emit(Resource.loading())
        if (loadFromDb != null) {
            loadFromDb.invoke().collect {
                if (shouldFetch(it)) {
                    fetchFromNetwork()
                } else {
                    collector.emit(Resource.success(it))
                }
            }
        } else {
            fetchFromNetwork()
        }
        return this
    }

    private suspend fun fetchFromNetwork() {
        return when (val result = call()) {
            is ApiSuccessResponse -> {
                val requestType = result.body
                val resultType = processResponse(requestType)
                saveCallResult?.invoke(resultType)

                if (loadFromDb != null) {
                    loadFromDb.invoke().collect {
                        collector.emit(Resource.success(it))
                    }
                } else {
                    collector.emit(Resource.success(resultType))
                }
            }
            is ApiEmptyResponse -> {
                if (loadFromDb != null) {
                    loadFromDb.invoke().collect {
                        collector.emit(Resource.success(it))
                    }
                } else {
                    collector.emit(Resource.success(null))
                }
            }
            is ApiErrorResponse -> {
                collector.emit(Resource.error(result.error))
            }
        }
    }

}
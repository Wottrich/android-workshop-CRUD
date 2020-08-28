package wottrich.github.io.androidworkshop_crud.data.resource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
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
    private val processResponse: (response: RequestType) -> ResultType,
    private val call: suspend () -> ApiResponse<RequestType>
) {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            setValue(Resource.loading())
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            getResult()
        }
        return this
    }

    private suspend fun getResult() {
        return when (val result = call()) {
            is ApiSuccessResponse -> {
                val process = processResponse(result.body)
                setValue(Resource.success(process))
            }
            is ApiEmptyResponse -> {
                setValue(Resource.success(null))
            }
            is ApiErrorResponse -> {
                setValue(Resource.error(result.error))
            }
        }
    }

    private fun setValue (newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

}
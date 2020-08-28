package wottrich.github.io.androidworkshop_crud.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSource
import wottrich.github.io.androidworkshop_crud.util.resource.Resource
import wottrich.github.io.androidworkshop_crud.model.User
import wottrich.github.io.androidworkshop_crud.util.AppDispatchers
import wottrich.github.io.androidworkshop_crud.util.resource.Status

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

class OverviewViewModel(
    private val service: UserDataSource,
    private val dispatchers: AppDispatchers
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    var mutableName = MutableLiveData<String>()
    private val name: String
        get() = mutableName.value ?: ""

    init {
        viewModelScope.launch(dispatchers.io) {
            service.loadUsers(true).collect {
                if (it.status == Status.SUCCESS) {
                    _users.postValue(it)
                }
            }
        }
    }

    //=====> UTILS
    private suspend fun Flow<Resource<List<User>>>.requestCallback () {
        this.collect {
            _users.postValue(it)
        }
    }

    //=====> SERVICES
    fun loadUsers() {
        viewModelScope.launch(dispatchers.main) {
            service.loadUsers(false).requestCallback()
        }
    }

    fun createUser () {
        val userName = name
        if (userName.isNotEmpty()) {
            mutableName.value = ""
            viewModelScope.launch(dispatchers.main) {
                service.createUser(userName).requestCallback()
            }
        } else {
            _errorMessage.value = "Preencha todos os campos"
        }
    }

    fun deleteUser (user: User) {
        viewModelScope.launch(dispatchers.main) {
            service.deleteUser(user).requestCallback()
        }
    }

}
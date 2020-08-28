package wottrich.github.io.androidworkshop_crud.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSource
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSourceImpl
import wottrich.github.io.androidworkshop_crud.data.resource.Resource
import wottrich.github.io.androidworkshop_crud.model.User
import wottrich.github.io.androidworkshop_crud.util.AppDispatchers

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

    private val _users = MediatorLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    var mutableName = MutableLiveData<String>()
    private val name: String
        get() = mutableName.value ?: ""

    //=====> UTILS
    private suspend fun LiveData<Resource<List<User>>>.requestCallback () {
        _users.removeSource(usersService)

        withContext(dispatchers.io) {
            usersService = this@requestCallback
        }

        _users.addSource(usersService) {
            _users.postValue(it)
        }

    }

    //=====> SERVICES

    private var usersService: LiveData<Resource<List<User>>> = MutableLiveData()

    fun loadUsers() {
        viewModelScope.launch(dispatchers.main) {
            service.loadUsers().requestCallback()
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
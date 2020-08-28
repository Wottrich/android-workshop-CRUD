package wottrich.github.io.androidworkshop_crud.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSource
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSourceImpl
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

class OverviewViewModel(
    private val service: UserDataSource
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    var mutableName = MutableLiveData<String>()
    private val name: String
        get() = mutableName.value ?: ""

    //=====> UTILS
    private fun requestCallback (users: List<User>?, messageError: String?) {
        _loading.value = false
        if (users != null) {
            _users.value = users
        } else {
            _errorMessage.value = messageError
        }
    }

    //=====> SERVICES
    fun loadUsers() {
        _loading.value = true
        service.loadUsers { users, messageError ->
            requestCallback(users, messageError)
        }
    }

    fun createUser () {
        _loading.value = true
        if (name.isNotEmpty()) {
            service.createUser(name) { users, messageError ->
                requestCallback(users, messageError)
            }
        } else {
            _errorMessage.value = "Preencha todos os campos"
        }
    }

    fun deleteUser (user: User) {
        _loading.value = true
        service.deleteUser(user) { users, messageError ->
            requestCallback(users, messageError)
        }
    }

}
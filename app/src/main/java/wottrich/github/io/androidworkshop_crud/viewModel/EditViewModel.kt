package wottrich.github.io.androidworkshop_crud.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSource
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSourceImpl
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */



class EditViewModel (
    private val service: UserDataSource = UserDataSourceImpl()
) : ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _userToEdit = MutableLiveData<User>()
    val userToEdit: LiveData<User>
        get() = _userToEdit

    val mutableName = MutableLiveData<String>()
    val name: String
        get() = mutableName.value ?: ""

    private val _successService = MutableLiveData<Unit>()
    val successService: LiveData<Unit>
        get() = _successService

    companion object {
        const val keyUserEdit = "userToEdit"
    }

    //=====> SCREEN
    fun loadExtras (extras: Bundle?) {
        val user = extras?.get(keyUserEdit)
        if (user != null) {
            _userToEdit.value = user as User
            mutableName.value = user.name
        } else {
            _errorMessage.value = "Extras não encontrado"
        }
    }

    //=====> SERVICES
    fun updateUser () {
        if (name.isNotEmpty() && _userToEdit.value != null) {
            _isLoading.value = true
            service.updateUser(_userToEdit.value!!.id, name) { _, messageError ->
                _isLoading.value = false
                if (messageError == null) {
                    _successService.value = Unit
                } else {
                    _errorMessage.value = messageError
                }
            }
        } else {
            _errorMessage.value = "Ocorreu um erro ao editar o usuário"
        }
    }

}
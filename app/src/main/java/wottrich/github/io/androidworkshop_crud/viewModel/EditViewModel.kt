package wottrich.github.io.androidworkshop_crud.viewModel

import android.os.Bundle
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
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
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */



class EditViewModel (
    private val service: UserDataSource,
    private val dispatchers: AppDispatchers
) : ViewModel(){

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _userToEdit = MutableLiveData<User>()
    val userToEdit: LiveData<User>
        get() = _userToEdit

    val mutableName = MutableLiveData<String>()
    val name: String
        get() = mutableName.value ?: ""

    private val _successService = MediatorLiveData<Resource<List<User>>>()
    val successService: LiveData<Resource<List<User>>>
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
            viewModelScope.launch(dispatchers.main) {

                service.updateUser(_userToEdit.value!!.id, name).collect {
                    _successService.postValue(it)
                }

            }
        } else {
            _errorMessage.value = "Ocorreu um erro ao editar o usuário"
        }
    }

}
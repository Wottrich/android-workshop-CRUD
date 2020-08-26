package wottrich.github.io.androidworkshop_crud.viewModel

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

interface OverviewViewModelInteraction {
    fun error(message: String?)
    fun updateUsers ()
    fun onEditing (name: String?)
}

class OverviewViewModel(
    private val service: UserDataSource = UserDataSourceImpl(),
    private val interaction: OverviewViewModelInteraction
) {

    var users: List<User> = listOf()

    val isEditing: Boolean
        get() = editingUser != null

    private var _name: String = ""

    private var editingUser: User? = null

    fun updateName (name: String) {
        this._name = name
    }

    private fun requestCallback (users: List<User>?, messageError: String?) {
        if (users != null) {
            this.users = users
            interaction.updateUsers()
        } else {
            interaction.error(messageError)
        }
    }

    fun editUser (user: User) {
        this.editingUser = user
        interaction.onEditing(user.name)
    }

    fun cancelEdit () {
        this.editingUser = null
        interaction.onEditing(null)
    }

    //=====> CRUD
    fun createUser () {
        if (_name.isNotEmpty()) {
            service.createUser(_name) { users, messageError ->
                requestCallback(users, messageError)
            }
        } else {
            interaction.error("Preencha todos os campos")
        }
    }

    fun loadUsers() {
        service.loadUsers { users, messageError ->
            requestCallback(users, messageError)
        }
    }

    fun updateUser () {
        if (editingUser != null && _name.isNotEmpty()) {
            service.updateUser(editingUser!!.id, _name) { users, messageError ->
                requestCallback(users, messageError)
                cancelEdit()
            }
        } else {
            interaction.error("Não foi possivel editar o usuário")
            cancelEdit()
        }
    }

    fun deleteUser (user: User) {
        service.deleteUser(user) { users, messageError ->
            requestCallback(users, messageError)
        }
    }

}
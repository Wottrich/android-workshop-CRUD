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
    fun reloadUsers ()
}

class OverviewViewModel(
    private val service: UserDataSource = UserDataSourceImpl(),
    private val interaction: OverviewViewModelInteraction
) {

    private var _users: List<User>? = null
    val users: List<User>
        get() = _users ?: listOf()

    private var _name: String? = null
    val name: String
        get() = _name ?: ""

    //=====> SCREEN
    fun setName (name: String) {
        _name = name
    }

    //=====> UTILS
    private fun requestCallback (users: List<User>?, messageError: String?) {
        if (users != null) {
            _users = users
            interaction.reloadUsers()
        } else {
            interaction.error(messageError)
        }
    }

    //=====> SERVICES
    fun loadUsers() {
        service.loadUsers { users, messageError ->
            requestCallback(users, messageError)
        }
    }

    fun createUser () {
        if (name.isNotEmpty()) {
            service.createUser(name) { users, messageError ->
                requestCallback(users, messageError)
            }
        } else {
            interaction.error("Preencha todos os campos")
        }
    }

    fun deleteUser (user: User) {
        service.deleteUser(user) { users, messageError ->
            requestCallback(users, messageError)
        }
    }

}
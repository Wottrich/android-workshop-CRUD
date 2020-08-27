package wottrich.github.io.androidworkshop_crud.viewModel

import android.os.Bundle
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

interface EditViewModelInteraction {
    fun error(message: String)
    fun loadScreen ()
    fun onEdited()
}

class EditViewModel (
    private val service: UserDataSource = UserDataSourceImpl(),
    private val interaction: EditViewModelInteraction
) {

    private var _userToEdit: User? = null
    val userToEdit: User?
        get() = _userToEdit

    private var _name: String? = null
    val name: String
        get() = _name ?: ""

    companion object {
        const val keyUserEdit = "userToEdit"
    }

    //=====> SCREEN
    fun loadExtras (extras: Bundle?) {
        val user = extras?.get(keyUserEdit)
        if (user != null) {
            val userToEdit = user as User
            _userToEdit = userToEdit
            _name = userToEdit.name
            interaction.loadScreen()
        } else {
            interaction.error("Extras não encontrado")
        }
    }

    fun setName (name: String) {
        _name = name
    }

    //=====> SERVICES
    fun updateUser () {
        if (name.isNotEmpty() && userToEdit != null) {
            service.updateUser(userToEdit!!.id, name) { _, messageError ->
                if (messageError == null) {
                    interaction.onEdited()
                } else {
                    interaction.error(messageError)
                }
            }
        } else {
            interaction.error("Ocorreu um erro ao editar o usuário")
        }
    }

}
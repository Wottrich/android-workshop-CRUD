package wottrich.github.io.androidworkshop_crud.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_overview.*
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.view.adapter.UserAdapter
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModelInteraction

class OverviewActivity : AppCompatActivity(), OverviewViewModelInteraction {

    private val activity = this

    private val viewModel: OverviewViewModel by lazy {
        OverviewViewModel(interaction = this)
    }

    private val adapter: UserAdapter by lazy {
        UserAdapter(this, viewModel.users).apply {
            onDelete = viewModel::deleteUser
            onEdit = viewModel::editUser
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        setupListeners()
        setupWatchers()
        setupRecycler()
        loadData()
    }

    private fun setupListeners () {

        btnRegister.setOnClickListener {
            if (viewModel.isEditing) {
                viewModel.updateUser()
            } else {
                viewModel.createUser()
                etName.setText("")
            }
        }

        btnCancel.setOnClickListener {
            viewModel.cancelEdit()
        }

    }

    private fun setupWatchers() {

        etName.doAfterTextChanged {
            viewModel.updateName(it.toString())
        }

    }

    private fun setupRecycler() {

        rvUsers.apply {
            adapter = activity.adapter
        }

    }

    private fun loadData() {
        showLoading(true)
        viewModel.loadUsers()
    }

    private fun showLoading(isVisible: Boolean) {
        rvUsers.isVisible = !isVisible
        progressBar.isVisible = isVisible
    }

    override fun error(message: String?) {
        Toast.makeText(this, message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()
    }

    override fun updateUsers() {
        showLoading(false)
        adapter.setItems(viewModel.users)
    }

    override fun onEditing(name: String?) {
        etName.setText(name)
        btnCancel.isVisible = name != null

        val text = if (name != null) R.string.activity_overview_edit_button_text
        else R.string.activity_overview_register_button_text
        btnRegister.setText(text)
    }

}
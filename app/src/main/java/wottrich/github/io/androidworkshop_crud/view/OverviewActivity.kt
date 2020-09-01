package wottrich.github.io.androidworkshop_crud.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.databinding.ActivityOverviewBinding
import wottrich.github.io.androidworkshop_crud.view.adapter.UserAdapter
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModelInteraction

class OverviewActivity : AppCompatActivity(), OverviewViewModelInteraction {

    private val activity = this

    private val viewModel: OverviewViewModel by lazy {
        OverviewViewModel(interaction = this)
    }

    private lateinit var binding: ActivityOverviewBinding

    private val adapter: UserAdapter by lazy {
        UserAdapter(this, viewModel.users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_overview)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview)

        setupListeners()
        setupWatchers()
        setupRecycler()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun setupListeners () {

        binding.apply {
            btnRegister.setOnClickListener {
                viewModel.createUser()
                binding.etName.setText("")
            }
        }

        adapter.onEdit = {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditViewModel.keyUserEdit, it)
            startActivity(intent)
        }

        adapter.onDelete = viewModel::deleteUser

    }

    private fun setupWatchers() {

        binding.apply {
            etName.doAfterTextChanged {
                viewModel.setName(it.toString())
            }
        }

    }

    private fun setupRecycler() {

        binding.apply {
            rvUsers.apply {
                adapter = activity.adapter
            }
        }

    }

    private fun loadData() {
        showLoading(true)
        viewModel.loadUsers()
    }

    private fun showLoading(isVisible: Boolean) {
        binding.apply {
            rvUsers.isVisible = !isVisible
            progressBar.isVisible = isVisible
        }
    }

    override fun error(message: String?) {
        Toast.makeText(this, message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()
    }

    override fun reloadUsers() {
        showLoading(false)
        adapter.setItems(viewModel.users)
    }

}
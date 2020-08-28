package wottrich.github.io.androidworkshop_crud.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.databinding.ActivityOverviewBinding
import wottrich.github.io.androidworkshop_crud.view.adapter.UserAdapter
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModel

class OverviewActivity : AppCompatActivity() {

    private val activity = this

    private val viewModel: OverviewViewModel by viewModel()

    private lateinit var binding: ActivityOverviewBinding

    private val adapter: UserAdapter by lazy {
        UserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview)

        setupListeners()
        setupRecycler()
        setupObservables()
        setupBinding()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun setupBinding () {
        binding.apply {
            lifecycleOwner = activity
            viewModel = activity.viewModel
        }
    }

    private fun setupListeners () {

        binding.apply {
            btnRegister.setOnClickListener {
                activity.viewModel.createUser()
                etName.setText("")
            }
        }

        adapter.onEdit = {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditViewModel.keyUserEdit, it)
            startActivity(intent)
        }

        adapter.onDelete = viewModel::deleteUser

    }

    private fun setupRecycler() {

        binding.apply {
            rvUsers.apply {
                adapter = activity.adapter
            }
        }

    }

    private fun setupObservables() {
        viewModel.apply {

            errorMessage.observe(activity) {
                Toast.makeText(activity, it ?: "Error desconhecido", Toast.LENGTH_SHORT).show()
            }

            users.observe(activity) {
                activity.adapter.setItems(it ?: listOf())
            }

        }
    }

    private fun loadData() {
        viewModel.loadUsers()
    }

}
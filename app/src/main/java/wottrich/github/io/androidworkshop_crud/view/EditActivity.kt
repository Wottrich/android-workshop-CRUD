package wottrich.github.io.androidworkshop_crud.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.archive.hideKeyboard
import wottrich.github.io.androidworkshop_crud.databinding.ActivityEditBinding
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModel

class EditActivity : AppCompatActivity() {

    private val activity = this
    private lateinit var binding: ActivityEditBinding

    private val viewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        viewModel.loadExtras(intent.extras)
        setupBinding()
        setupListeners()
        setupObservables()
    }

    private fun setupBinding () {
        binding.apply {
            lifecycleOwner = activity
            viewModel = activity.viewModel
        }
    }

    private fun setupListeners () {
        binding.apply {
            btnEdit.setOnClickListener {
                hideKeyboard(it?.windowToken)
                activity.viewModel.updateUser()
            }

            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun setupObservables () {
        viewModel.apply {
            errorMessage.observe(activity) {
                Toast.makeText(activity, it ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()
            }

            userToEdit.observe(activity) {
                binding.etName.setText(it?.name)
            }

            successService.observe(activity) {
                finish()
            }
        }
    }
}
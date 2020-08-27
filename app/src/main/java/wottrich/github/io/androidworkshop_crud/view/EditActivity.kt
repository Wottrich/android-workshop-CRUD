package wottrich.github.io.androidworkshop_crud.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.archive.hideKeyboard
import wottrich.github.io.androidworkshop_crud.databinding.ActivityEditBinding
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModelInteraction

class EditActivity : AppCompatActivity(), EditViewModelInteraction {

    private val activity = this
    private lateinit var binding: ActivityEditBinding

    private val viewModel: EditViewModel by lazy {
        EditViewModel(
            interaction = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        viewModel.loadExtras(intent.extras)
        setupListeners()
        setupWatchers()
    }

    private fun setupListeners () {
        binding.apply {
            btnEdit.setOnClickListener {
                hideKeyboard(it?.windowToken)
                progressBar.isVisible = true
                activity.viewModel.updateUser()
            }

            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun setupWatchers () {

        binding.apply {
            etName.doAfterTextChanged {
                activity.viewModel.setName(it.toString())
            }
        }

    }

    override fun error(message: String) {
        binding.progressBar.isVisible = false
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun loadScreen() {
        binding.etName.setText(viewModel.name)
    }

    override fun onEdited() {
        binding.progressBar.isVisible = false
        finish()
    }
}
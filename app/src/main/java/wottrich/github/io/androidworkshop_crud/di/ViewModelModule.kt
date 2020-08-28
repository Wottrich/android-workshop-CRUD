package wottrich.github.io.androidworkshop_crud.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wottrich.github.io.androidworkshop_crud.viewModel.EditViewModel
import wottrich.github.io.androidworkshop_crud.viewModel.OverviewViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
val viewModelModule = module {

    viewModel { EditViewModel(get()) }

    viewModel { OverviewViewModel(get()) }

}
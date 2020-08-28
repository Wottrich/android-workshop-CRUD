package wottrich.github.io.androidworkshop_crud.di

import org.koin.dsl.module
import wottrich.github.io.androidworkshop_crud.data.network.RetrofitInstance

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
val networkModule = module {

    single { RetrofitInstance.api }

}
package wottrich.github.io.androidworkshop_crud.di

import org.koin.dsl.module
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSource
import wottrich.github.io.androidworkshop_crud.data.datasource.UserDataSourceImpl

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
val dataSourceModule = module {

    single<UserDataSource> { UserDataSourceImpl() }

}
package wottrich.github.io.androidworkshop_crud.di

import org.koin.dsl.module
import wottrich.github.io.androidworkshop_crud.data.database.AppDatabase
import wottrich.github.io.androidworkshop_crud.data.repository.UserRepository
import wottrich.github.io.androidworkshop_crud.data.repository.UserRepositoryImpl

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
object DatabaseModule {
    val databaseModule = module {
        single { AppDatabase.getInstance(get()).userDao() }
    }

    val repositoryModule = module {
        single<UserRepository> { UserRepositoryImpl(get()) }
    }
}
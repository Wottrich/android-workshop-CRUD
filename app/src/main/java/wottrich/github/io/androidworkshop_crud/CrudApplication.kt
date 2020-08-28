package wottrich.github.io.androidworkshop_crud

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import wottrich.github.io.androidworkshop_crud.di.appModule

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
class CrudApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CrudApplication)
            modules(appModule)
        }
    }

}
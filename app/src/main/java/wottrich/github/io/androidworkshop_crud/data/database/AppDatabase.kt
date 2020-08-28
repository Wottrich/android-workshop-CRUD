package wottrich.github.io.androidworkshop_crud.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wottrich.github.io.androidworkshop_crud.data.dao.UserDao
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 28/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "CRUDApplicationDatabase"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase (context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

    }

}
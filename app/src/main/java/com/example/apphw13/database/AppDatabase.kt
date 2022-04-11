package com.example.apphw13.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apphw13.models.Account


@Database(entities = [Account::class], version = 1 )
abstract class AppDatabase : RoomDatabase(){
    abstract fun accountDao(): AccountDao

    companion object{
        var instance : AppDatabase?=null
        fun getAppDatabase(context: Context):AppDatabase?{
            if (instance==null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "AccountDB"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}
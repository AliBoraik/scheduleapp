package com.example.scheduleapp

import android.app.Application
import androidx.room.Room
import com.example.scheduleapp.DataBase.AppDatabase

class MyApplication: Application() {

    private var database: AppDatabase? = null

    companion object {
        lateinit var instance: MyApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database").allowMainThreadQueries()
                .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }
}
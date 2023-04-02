package com.example.scheduleapp.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scheduleapp.Lesson


@Database(entities = [Lesson::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): employeeDao?
}
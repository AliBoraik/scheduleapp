package com.example.scheduleapp.DataBase

import androidx.room.*
import com.example.scheduleapp.Lesson


@Dao
interface employeeDao {
    @get:Query("SELECT * FROM Lesson")
    val all: List<Lesson?>?

    @Query("SELECT * FROM Lesson WHERE id = :id")
    fun getById(id: Long): Lesson?

    @Insert
    fun insert(lesson: Lesson?)

    @Update
    fun update(lesson: Lesson?)

    @Delete
    fun delete(lesson: Lesson?)


}
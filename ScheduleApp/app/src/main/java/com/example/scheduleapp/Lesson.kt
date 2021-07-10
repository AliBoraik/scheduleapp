package com.example.scheduleapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Lesson {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var color: Int? = null
    var day: String? = null
    var dayOrder: Int? = null
    var dateFrom: String? = null
    var dateTo: String? = null
}
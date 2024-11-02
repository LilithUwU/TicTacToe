package com.example.tictactoe.db

import androidx.room.TypeConverter
import java.util.Date

//To be able to save date in database
class Converters {

    @TypeConverter
    fun fromDate(date : Date) : Long{
        return date.time
    }

    @TypeConverter
    fun toDate(time : Long) : Date{
        return Date(time)
    }

}
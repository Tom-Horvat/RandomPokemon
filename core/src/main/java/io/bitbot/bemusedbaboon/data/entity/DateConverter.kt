package io.bitbot.bemusedbaboon.data.entity

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): LocalDateTime? = dateLong?.let {
        Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDateTime()
    }

    @TypeConverter
    fun fromDate(date: LocalDateTime?) = date?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
}
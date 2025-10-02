package io.bitbot.bemusedbaboon.data.entity

import java.time.LocalDateTime

interface DbEntry {
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
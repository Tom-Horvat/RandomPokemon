package io.bitbot.bemusedbaboon.core.data.entity

import java.time.LocalDateTime

interface Timestamp {
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
package com.valentinerutto.datacacheroom.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "NewsList")
data class NewsEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
)
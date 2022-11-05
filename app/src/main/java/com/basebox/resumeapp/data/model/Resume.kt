package com.basebox.resumeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Resume(
    @PrimaryKey
    val id: Int,
    val title: String,
    val name: String,
    val image: Int,
    val phone: String,
    val experience: String,
    val about: String,
    val linkedin: String,
    val github: String,
    val skills: String
)

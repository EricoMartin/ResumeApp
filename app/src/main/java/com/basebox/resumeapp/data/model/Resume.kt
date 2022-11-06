package com.basebox.resumeapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Resume(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val name: String,
    val email: String,
    val image: String,
    val phone: String,
    val experience: String,
    val qualification: String,
    val linkedin: String,
    val github: String,
    val skills: String
): Parcelable

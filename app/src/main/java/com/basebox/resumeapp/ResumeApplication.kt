package com.basebox.resumeapp

import android.app.Application
import com.basebox.resumeapp.data.database.ResumeDb
import com.basebox.resumeapp.data.repo.DefaultRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ResumeApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ResumeDb.getInstance(this) }
    val repository by lazy { DefaultRepository(database.resumeDao) }
}
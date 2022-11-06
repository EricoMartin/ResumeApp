package com.basebox.resumeapp.data.repo

import androidx.lifecycle.LiveData
import com.basebox.resumeapp.data.model.Resume
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    val getAllResumes: Flow<List<Resume>>
    suspend fun getResume(username: String): Resume
    suspend fun insertResume(resume: Resume)
    suspend fun refreshDB()
}
package com.basebox.resumeapp.data.repo

import android.util.Log
import androidx.lifecycle.asLiveData
import com.basebox.resumeapp.data.dao.ResumeDao
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRepository @Inject constructor (
    private var resDao: ResumeDao
        ): MainRepository {
    override val getAllResumes: Flow<List<Resume>> = resDao.getAll()

    override suspend fun getResume(username: String):Resume{
        val getStuff = withContext(Dispatchers.IO) {
             resDao.getResume(username)
        }
        Resource.Success(getStuff)
        return getStuff
    }

    override suspend fun insertResume(resume: Resume) {
        withContext(Dispatchers.IO) {
            resDao.insertResume(resume)
        }
    }

    override suspend fun refreshDB() {
        return withContext(Dispatchers.IO){
            resDao.deleteAllResumes()
        }
    }
}
package com.basebox.resumeapp.data.repo

import android.util.Log
import com.basebox.resumeapp.data.dao.ResumeDao
import com.basebox.resumeapp.data.model.Resume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRepository @Inject constructor (
    private var resDao: ResumeDao
        ): MainRepository {
    override val getAllResumes: Flow<List<Resume>> = resDao.getAll()

    override suspend fun getResume(username: String): Resume {
        val getResFromDB = withContext(Dispatchers.IO) {
            resDao.getResume(username)}
        Log.d("DefaultRepo", "$getResFromDB")
        return getResFromDB
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
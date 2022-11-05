package com.basebox.resumeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basebox.resumeapp.data.model.Resume
import kotlinx.coroutines.flow.Flow

@Dao
interface ResumeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResume(resume: Resume)

    @Query("SELECT * FROM resume ORDER BY name ASC")
    fun getAll(): Flow<List<Resume>>

    @Query("SELECT * from Resume WHERE name Like :resume Limit 1")
    fun getResume(resume: String): Resume

    @Query("Delete from resume")
    fun deleteAllResumes()

}
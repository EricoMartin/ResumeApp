package com.basebox.resumeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basebox.resumeapp.data.dao.ResumeDao
import com.basebox.resumeapp.data.model.Resume

@Database(entities = [Resume::class], version = 3, exportSchema = false)
abstract class ResumeDb: RoomDatabase() {
    abstract val resumeDao: ResumeDao

    companion object {
        @Volatile
        private var INSTANCE: ResumeDb? = null

        fun getInstance(context: Context): ResumeDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, ResumeDb::class.java,
                        "resume_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
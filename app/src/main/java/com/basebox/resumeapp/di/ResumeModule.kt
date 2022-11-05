package com.basebox.resumeapp.di

import android.content.Context
import com.basebox.resumeapp.data.dao.ResumeDao
import com.basebox.resumeapp.data.database.ResumeDb
import com.basebox.resumeapp.data.repo.DefaultRepository
import com.basebox.resumeapp.data.repo.MainRepository
import com.basebox.resumeapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResumeModule {
    @Singleton
    @Provides
    fun provideMainRepository(resDao: ResumeDao):
            MainRepository = DefaultRepository(resDao)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): ResumeDb {
        return ResumeDb.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDao(resDB: ResumeDb): ResumeDao {
        return resDB.resumeDao
    }

}
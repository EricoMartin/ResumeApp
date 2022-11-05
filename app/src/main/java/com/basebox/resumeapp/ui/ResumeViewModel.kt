package com.basebox.resumeapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.data.repo.MainRepository
import com.basebox.resumeapp.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatcher: DispatcherProvider
): ViewModel() {
    init {
        try {
            viewModelScope.launch {
               repository.refreshDB()
            }
        } catch (err: Exception) {
            Log.d("ResumeViewModel", "${err.message}")
        }
    }


    private val _resumeResult = MutableStateFlow<ResumeEvent>(ResumeEvent.Empty)
    val resumeResult: StateFlow<ResumeEvent> = _resumeResult

    val getALlResumes: LiveData<List<Resume>> = repository.getAllResumes.asLiveData()

    fun showResume(username: String){
        viewModelScope.launch(dispatcher.io) {
            _resumeResult.value = ResumeEvent.Loading
            val res = repository.getResume(username)
            if (res.id.equals(null)){
                _resumeResult.value = ResumeEvent.Failure("An Error Occurred!")
            } else {
                _resumeResult.value = ResumeEvent.Success("$res ")
            }
        }

    }
    suspend fun createResume(resume: Resume) = viewModelScope.launch(dispatcher.io) {
        repository.insertResume(resume)
    }

    suspend fun clearDb() = try {
        viewModelScope.launch {
            repository.refreshDB()
        }
    } catch (err: Exception) {
        Log.d("ResumeViewModel", "${err.message}")
    }

    sealed class ResumeEvent {
        class Success(val result: String) : ResumeEvent()
        class Failure(val error: String) : ResumeEvent()
        object Loading : ResumeEvent()
        object Empty : ResumeEvent()
    }
}
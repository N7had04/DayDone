package com.nhdtech.apps.daydone.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import com.nhdtech.apps.daydone.notification.NotificationScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _selectedDeadlineText = MutableLiveData<String>()
    val selectedDeadlineText: LiveData<String> = _selectedDeadlineText
    private var selectedDeadlineMillis: Long = 0L

    private val _taskSaved = MutableLiveData<Boolean>()
    val taskSaved: LiveData<Boolean> = _taskSaved

    fun setSelectedDeadline(calendar: Calendar) {
        selectedDeadlineMillis = calendar.timeInMillis

        val format = SimpleDateFormat(
            "dd MMM yyyy, HH:mm",
            Locale.getDefault()
        )

        _selectedDeadlineText.value = format.format(calendar.time)
    }

    fun addTask(title: String, description: String?) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                description = description,
                deadline = selectedDeadlineMillis
            )
            val id = addTaskUseCase.execute(task)
            Log.d("AddTaskViewModel", "Task inserted with id: $id")
            NotificationScheduler.schedule(context, task.copy(id = id.toInt()))
            Log.d("AddTaskViewModel", "Alarm scheduled for id: $id")
            _taskSaved.value = true
        }
    }
}
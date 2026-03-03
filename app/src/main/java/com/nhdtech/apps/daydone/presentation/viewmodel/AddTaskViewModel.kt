package com.nhdtech.apps.daydone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
): ViewModel() {

    private val _selectedDeadlineText = MutableLiveData<String>()
    val selectedDeadlineText: LiveData<String> = _selectedDeadlineText

    private var selectedDeadlineMillis: Long = 0L

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
            addTaskUseCase.execute(
                Task(
                    title = title,
                    description = description,
                    deadline = selectedDeadlineMillis
                )
            )
        }
    }
}
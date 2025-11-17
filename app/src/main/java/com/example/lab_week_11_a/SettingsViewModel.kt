package com.example.lab_week_11_a

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        viewModelScope.launch {
            settingsStore.text.collect { value ->
                _textLiveData.value = value
            }
        }
    }

    fun saveText(text: String) {
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}

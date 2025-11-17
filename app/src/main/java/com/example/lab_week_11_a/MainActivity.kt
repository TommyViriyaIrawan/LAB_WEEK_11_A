package com.example.lab_week_11_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get DataStore wrapper from Application
        val settingsStore = (application as SettingsApplication).settingsStore

        // ViewModel with factory
        val settingsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(settingsStore) as T
                }
            }
        )[SettingsViewModel::class.java]

        // Observing DataStore LiveData
        settingsViewModel.textLiveData.observe(this) { value ->
            findViewById<TextView>(R.id.activity_main_text_view).text = value
        }

        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val inputText = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            settingsViewModel.saveText(inputText)
        }
    }
}

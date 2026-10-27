package com.example.praktikum1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.praktikum1.data.TaskDatabase
import com.example.praktikum1.data.TaskRepository
import com.example.praktikum1.ui.AppNavigation
import com.example.praktikum1.ui.TaskViewModel
import com.example.praktikum1.ui.TaskViewModelFactory
import com.example.praktikum1.ui.theme.Praktikum1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Membuat Database Room
        val database = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task_db"
        ).build()

        // 2. Membuat Repository
        val repository = TaskRepository(database.taskDao())

        // 3. Membuat ViewModel
        val viewModel: TaskViewModel by viewModels {
            TaskViewModelFactory(repository)
        }

        setContent {
            // "Praktikum1Theme" adalah tema bawaan aplikasi Anda
            Praktikum1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 4. Memanggil Navigasi Utama
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}
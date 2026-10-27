package com.example.praktikum1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    // NavController bertugas mengingat kita sedang di layar yang mana
    val navController = rememberNavController()

    // NavHost adalah wadah untuk menampilkan layar-layarnya
    NavHost(navController = navController, startDestination = "taskList") {

        // Rute 1: Layar Daftar Tugas-tugas
        composable("taskList") {
            TaskListScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate("addTask") },
                onNavigateToDetail = { taskId ->
                    // Fitur layar detail akan kita kerjakan nanti
                }
            )
        }

        // Rute 2: Layar Form Tambah  Tugas
        composable("addTask") {
            TaskFormScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() } // popBackStack = kembali ke layar sebelumnya
            )
        }
    }
}
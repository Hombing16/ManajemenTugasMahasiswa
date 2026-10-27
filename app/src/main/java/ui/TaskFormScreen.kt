package com.example.praktikum1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum1.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    // Menyimpan state/status dari setiap kolom isian (form)
    var mataKuliah by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var prioritas by remember { mutableStateOf("Sedang") }
    var status by remember { mutableStateOf("Belum") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Tugas Baru") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Agar layar bisa di-scroll jika keyboard muncul
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = mataKuliah,
                onValueChange = { mataKuliah = it },
                label = { Text("Mata Kuliah") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = judul,
                onValueChange = { judul = it },
                label = { Text("Judul Tugas") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = deskripsi,
                onValueChange = { deskripsi = it },
                label = { Text("Deskripsi Tugas") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3 // Membuat kotak teks lebih besar untuk deskripsi
            )
            OutlinedTextField(
                value = deadline,
                onValueChange = { deadline = it },
                label = { Text("Deadline (Contoh: 15 Nov 2026)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = prioritas,
                onValueChange = { prioritas = it },
                label = { Text("Prioritas (Rendah / Sedang / Tinggi)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = status,
                onValueChange = { status = it },
                label = { Text("Status (Belum / Proses / Selesai)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol untuk menyimpan data ke database
            Button(
                onClick = {
                    // Validasi sederhana: pastikan mata kuliah dan judul tidak kosong
                    if (mataKuliah.isNotBlank() && judul.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                mataKuliah = mataKuliah,
                                judul = judul,
                                deskripsi = deskripsi,
                                deadline = deadline,
                                prioritas = prioritas,
                                status = status
                            )
                        )
                        onNavigateBack() // Kembali ke layar daftar setelah menyimpan
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan Tugas")
            }
        }
    }
}
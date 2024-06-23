package com.example.student

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.student.service.MyFireBaseMessagingService
import com.example.student.ui.navigation.NavGraph

@Composable
fun StudentApp(navController: NavHostController = rememberNavController()) {
    NavGraph(navController = navController)
}
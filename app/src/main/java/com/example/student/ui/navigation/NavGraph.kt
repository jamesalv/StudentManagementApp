package com.example.student.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.student.ui.edit.EditDestination
import com.example.student.ui.edit.EditScreen
import com.example.student.ui.entry.EntryDestination
import com.example.student.ui.entry.EntryScreen
import com.example.student.ui.home.HomeDestination
import com.example.student.ui.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToUpdate = { navController.navigate("${EditDestination.route}/$it") },
                navigateToEntry = { navController.navigate(EntryDestination.route) }
            )
        }

        composable(route = EntryDestination.route) {
            EntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.ID){
                type = NavType.IntType
            })
        ){
            EditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
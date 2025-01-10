package com.joinstoriessdk.project.compose_app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joinstoriessdk.project.compose_app.ui.screen.DashboardScreen
import com.joinstoriessdk.project.compose_app.ui.screen.HomeScreen
import com.joinstoriessdk.project.compose_app.ui.screen.NotificationScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) { HomeScreen() }
        composable(NavigationItem.Search.route) { DashboardScreen() }
        composable(NavigationItem.Profile.route) { NotificationScreen() }
    }
}

// Utility to get the current route
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

// Navigation Items
sealed class NavigationItem(val route: String, val title: String, val icon: ImageVector) {
    data object Home : NavigationItem("home", "Home", Icons.Default.Home)
    data object Search : NavigationItem("dashboard", "Dashboard", Icons.Default.DateRange)
    data object Profile : NavigationItem("notification", "Notifications", Icons.Default.Notifications)
}
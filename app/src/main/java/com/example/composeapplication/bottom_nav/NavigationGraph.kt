package com.example.composeapplication.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Network.screenRoute) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screenRoute) {
            AddPostScreen()
        }
        composable(BottomNavItem.Notifications.screenRoute) {
            NotificationScreen()
        }
    }
}
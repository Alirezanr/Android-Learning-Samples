package com.example.composeapplication.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screenRoute: String
) {
    object Home : BottomNavItem(
        title = "Home",
        icon = Icons.Default.Home,
        screenRoute = Routes.HOME_SCREEN
    )

    object Network : BottomNavItem(
        title = "My Network",
        icon = Icons.Default.People,
        screenRoute = Routes.NETWORK_SCREEN
    )

    object AddPost : BottomNavItem(
        title = "Add Post",
        icon = Icons.Default.PostAdd,
        screenRoute = Routes.ADD_POST_SCREEN
    )

    object Notifications : BottomNavItem(
        title = "Notifications",
        icon = Icons.Default.Notifications,
        screenRoute = Routes.NOTIFICATIONS_SCREEN
    )
}
package com.example.composeapplication.bottom_nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composeapplication.R

@Composable
fun CustomBottomNavigation(navController: NavController) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Network,
        BottomNavItem.AddPost,
        BottomNavItem.Notifications,
    )

    BottomNavigation(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .paint(
                painter = painterResource(R.drawable.a),
                contentScale = ContentScale.FillWidth
            )
            .background(Color.Black.copy(alpha = 0.9f)),
        //To use Modifier.background(...).paint(...) we have to set backgroundColor as transparent.
        backgroundColor = Color.Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            val isSelectedItem = item.screenRoute == currentRoute

            BottomNavigationItem(
                icon = {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .fillMaxHeight(0.7f)
                            .fillMaxWidth(0.9f)
                            .background(
                                if (isSelectedItem)
                                    colorResource(id = R.color.purple_200)
                                else
                                    Color.Transparent
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (isSelectedItem) colorResource(id = R.color.purple_700)
                            else Color.White.copy(alpha = 0.4f)
                        )

                        AnimatedVisibility(
                            visible = isSelectedItem
                        ) {
                            Text(
                                text = item.title,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = false,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
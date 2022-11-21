package com.example.composeapplication.bottom_nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
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

    //To make background of bottom nav blur I used Box with a blurred Image and BottomNavigation in it.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        //Blurred image as background.
        Image(
            painter = painterResource(R.drawable.a),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    radiusX = 16.dp,
                    radiusY = 16.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )

        BottomNavigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            //To remove white background:
            elevation = 0.dp,
            //To use Modifier.background(...).paint(...) we have to set backgroundColor as transparent.
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            bottomNavItems.forEach { item ->
                val isSelectedItem = item.screenRoute == currentRoute

                BottomNavigationItem(
                    icon = {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .fillMaxSize()
                                .background(
                                    if (isSelectedItem)
                                        Color.Black.copy(alpha = 0.5f)
                                    else
                                        Color.Transparent
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = if (isSelectedItem) colorResource(id = R.color.purple_200)
                                else Color.White.copy(alpha = 0.6f)
                            )

                            AnimatedVisibility(
                                visible = isSelectedItem
                            ) {
                                Text(
                                    text = item.title,
                                    fontSize = 11.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    },
                    selected = isSelectedItem,
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
}

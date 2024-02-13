package com.valentinerutto.datacacheroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
NavHost(navController =navController , startDestination =Screen.NEWSLIST.name , route = NavigationItem.NewsList.route)
}
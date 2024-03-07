package com.valentinerutto.datacacheroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.ui.ErrorScreen
import com.valentinerutto.datacacheroom.ui.MainView
import com.valentinerutto.datacacheroom.ui.NewsDetailScreen
import com.valentinerutto.datacacheroom.ui.composables.connectivityState
import com.valentinerutto.datacacheroom.utils.ConnectionState

@Composable
fun AppNavGraph(
    navController: NavHostController, modifier: Modifier, newsUiState: NewsViewModel.ArticleUiState,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.NewsList.route,
        modifier = modifier
    ) {

        composable(route = NavigationItem.NewsList.route) {
            val connection by connectivityState()

            val isConnected = connection === ConnectionState.Available

            MainView(newsUiState = newsUiState,isConnected, onNewsItemSelected = {
                val route = NavigationItem.NewsDetails.createRoute(it)
                navController.navigate(route)
            })
        }

        composable(route = NavigationItem.NewsDetails.route) { backstackEntry ->

            val newsItemPosition =
                backstackEntry.arguments?.getString("newsItemPosition")?.toInt() ?: 0

            val newsItem = newsUiState.article[newsItemPosition]
            NewsDetailScreen(
                newsItem
            )

        }
    }
}
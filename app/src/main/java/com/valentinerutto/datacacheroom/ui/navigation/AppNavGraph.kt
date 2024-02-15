package com.valentinerutto.datacacheroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.ui.MainView
import com.valentinerutto.datacacheroom.ui.NewsDetailScreen

@Composable
fun AppNavGraph(
    navController: NavHostController, modifier: Modifier, newsUiState: NewsViewModel.ArticleUiState
) {
    NavHost(
        navController = navController, startDestination =  NavigationItem.NewsList.route, modifier = modifier
    ) {

        composable(route = NavigationItem.NewsList.route) {

            MainView(newsUiState = newsUiState, onNewsItemSelected = {
                val route = NavigationItem.NewsDetails.createRoute(it)
                navController.navigate(route)
            })
        }

        composable(route = NavigationItem.NewsDetails.route) { backstackEntry ->

            val newsItemPosition = backstackEntry.arguments?.getInt("newsItemPosition") ?: 0

            val newsItem = newsUiState.article[newsItemPosition]

            NewsDetailScreen(
                newsItem, modifier = modifier
            )

        }
    }
}
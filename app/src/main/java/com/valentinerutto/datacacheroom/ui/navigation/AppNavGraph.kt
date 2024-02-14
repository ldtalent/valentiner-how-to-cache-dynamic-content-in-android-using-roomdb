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
    navController: NavHostController, modifier: Modifier,
    newsUiState: NewsViewModel.ArticleUiState
) {
    NavHost(
        navController = navController, startDestination = Screen.NEWSLIST.name, modifier = modifier
    ) {

        composable(route = Screen.NEWSLIST.name) {
            MainView(newsUiState = newsUiState)
        }

        composable(route = Screen.NEWSDETAILS.name) { backstackEntry ->

            val newsItemPosition = backstackEntry.arguments?.getInt("newsItemPosition")

            val newsItem = newsUiState.article[newsItemPosition!!]

            NewsDetailScreen(
                newsItem, modifier = modifier
            )
        }
    }
}
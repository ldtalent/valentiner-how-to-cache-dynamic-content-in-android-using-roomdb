package com.valentinerutto.datacacheroom.ui.navigation

sealed class NavigationItem(val route: String) {
    object NewsListFromErrorPage : NavigationItem("newsListFromErrorPage")
    object NewsList : NavigationItem("newsList")
    object NewsDetails : NavigationItem("newsDetails/{newsItemPosition}") {
        fun createRoute(newsItemPosition: Int) = "newsDetails/$newsItemPosition"
    }
}
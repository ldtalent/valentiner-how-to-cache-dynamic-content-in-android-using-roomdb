package com.valentinerutto.datacacheroom.ui.navigation


enum class Screen {
    NEWSLIST, NEWSDETAILS,
}

sealed class NavigationItem(val route: String) {
    object NewsList : NavigationItem(Screen.NEWSLIST.name)
    object NewsDetails : NavigationItem(Screen.NEWSDETAILS.name + "/{newsItemPosition}") {
        fun createRoute(newsItemPosition: Int) = Screen.NEWSDETAILS.name + "/" + "$newsItemPosition"
    }
}
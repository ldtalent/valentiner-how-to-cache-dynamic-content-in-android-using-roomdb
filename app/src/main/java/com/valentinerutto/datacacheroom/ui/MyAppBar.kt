package com.valentinerutto.datacacheroom.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.valentinerutto.datacacheroom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(navController: NavController, scrollBehavior: TopAppBarScrollBehavior) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack = navController.previousBackStackEntry

    val isBackButtonVisible by remember {
        derivedStateOf {
            navController.previousBackStackEntry != null
        }
    }
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_label_name)) },

        navigationIcon =
        {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                if (isBackButtonVisible) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_news_small),
                        contentDescription = "Back"
                    )

                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_news_small),
                        contentDescription = "Back"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )


    )
}
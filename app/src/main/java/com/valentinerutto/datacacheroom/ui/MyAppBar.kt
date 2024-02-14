package com.valentinerutto.datacacheroom.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    TopAppBar(
        title = { Text("My News") },

        actions = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }

        }
    )}
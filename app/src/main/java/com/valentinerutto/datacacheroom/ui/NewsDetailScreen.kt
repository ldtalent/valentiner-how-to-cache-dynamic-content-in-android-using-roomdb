package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity

@Composable
fun NewsDetailScreen(newsItem: NewsEntity,
                     modifier: Modifier) {

    Box {
        Column {

            ImageComposable(imageUrl = newsItem.imageUrl,
                modifier = modifier)

            Text(text = newsItem.title)

            Text(text = newsItem.content)
        }
    }

}
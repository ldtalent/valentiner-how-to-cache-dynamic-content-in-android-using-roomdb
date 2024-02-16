package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity

@Composable
fun NewsDetailScreen(newsItem: NewsEntity) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp) ){

        Column(
        ) {

            ImageComposable(imageUrl = newsItem.imageUrl, modifier = Modifier.fillMaxWidth())
            Text(text = newsItem.title, fontSize = 16.sp)
            Text(text = newsItem.description, fontSize = 16.sp)
            Text(text = newsItem.content, fontSize = 16.sp)
        }


    }
}
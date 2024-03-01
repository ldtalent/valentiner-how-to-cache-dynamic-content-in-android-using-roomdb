package com.valentinerutto.datacacheroom.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity

@Composable
fun NewsDetailScreen(newsItem: NewsEntity) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)


    ) {


        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = newsItem.title,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.padding(1.dp))
        Text(
            text = newsItem.sourceUrl,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {

                    val urlIntent = Intent(
                        Intent.ACTION_VIEW, Uri.parse(newsItem.sourceUrl)
                    )
                    context.startActivity(urlIntent)
                })
        Spacer(modifier = Modifier.padding(1.dp))
        Text(text = newsItem.content, fontSize = 16.sp)
        Spacer(modifier = Modifier.padding(1.dp))

        ImageComposable(
            imageUrl = newsItem.imageUrl,
            modifier = Modifier.aspectRatio(16f / 9f)
        )

    }

}
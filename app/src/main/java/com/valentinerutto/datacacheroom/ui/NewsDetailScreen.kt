package com.valentinerutto.datacacheroom.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity

@Composable
fun NewsDetailScreen(newsItem: NewsEntity) {
    val context = LocalContext.current
    Text(text = newsItem.title, fontSize = 16.sp)

    ConstraintLayout {
        val (texturl, texttitle,image,text) = createRefs()
        ImageComposable(imageUrl = newsItem.imageUrl, modifier = Modifier.constrainAs(image){top.linkTo(parent.top,margin=8.dp)}.padding(1.dp))
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = newsItem.title,modifier=Modifier.constrainAs(texttitle){top.linkTo(image.bottom,margin=8.dp)}, fontSize = 16.sp)
        Spacer(modifier = Modifier.padding(1.dp))
        Text(
            text = newsItem.sourceUrl,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(texturl){top.linkTo(parent.bottom,margin=8.dp)}.clickable {

                val urlIntent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(newsItem.sourceUrl)
                )
                context.startActivity(urlIntent)
            })
        Spacer(modifier = Modifier.padding(1.dp))
        Text(text = newsItem.content,modifier=Modifier.constrainAs(text){top.linkTo(texturl.top,margin=8.dp)}, fontSize = 16.sp)
        Spacer(modifier = Modifier.padding(1.dp))


    }

}
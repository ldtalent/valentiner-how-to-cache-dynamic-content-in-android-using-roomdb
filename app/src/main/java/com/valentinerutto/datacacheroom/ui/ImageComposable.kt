package com.valentinerutto.datacacheroom.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.valentinerutto.datacacheroom.R

@Composable
fun ImageComposable(imageUrl:String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        alignment = Alignment.CenterStart,
        model = imageUrl,
        contentDescription = stringResource(R.string.photo),    )
}
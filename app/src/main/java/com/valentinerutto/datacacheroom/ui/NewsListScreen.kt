package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinerutto.datacacheroom.Greeting
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainView() {

    val viewmodel = koinViewModel<NewsViewModel>()
    val newsUiState = viewmodel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Greeting("Android")


        if (newsUiState.value.loading) {
            LoadingView()
        }

        if (!newsUiState.value.error.isNullOrBlank()) {
            ErrorScreen(modifier = Modifier.fillMaxSize(), newsUiState.value.error)
        }

        if (!newsUiState.value.article.isNullOrEmpty()) {
            NewsListScreen(newsEntity = newsUiState.value.article)
        }


    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    newsEntity: List<NewsEntity>
) {

    Box(modifier) {
        Text(text = "NewsListScreen")
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(newsEntity) { news ->
                NewsArticleItem(modifier = modifier.animateItemPlacement(), newsArticle = news)
            }
        }
    }
}

@Composable
fun NewsArticleItem(modifier: Modifier, newsArticle: NewsEntity) {
    Card(modifier = modifier) {
        Column {
            ImageComposable(imageUrl = newsArticle.imageUrl, modifier = modifier)
            Text(text = newsArticle.content, style = MaterialTheme.typography.displayMedium)
        }
    }
}


@Composable
fun LoadingView() {
    CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.Magenta)
}

@Composable
fun ColumnScope.ErrorScreen(modifier: Modifier, errorMsg: String) {
    //, onTryAgainClicked: () -> Unit
    Spacer(modifier = Modifier.weight(0.5f))
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = errorMsg,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )
        Button(
            onClick = {
                // onTryAgainClicked()
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
        }
    }
    Spacer(modifier = Modifier.weight(0.5f))
}

@Preview(name = "NewsListScreen")
@Composable
private fun PreviewNewsListScreen() {
    MainView()
}
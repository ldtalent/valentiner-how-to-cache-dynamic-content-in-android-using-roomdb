package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity

@Composable
fun MainView(newsUiState: NewsViewModel.ArticleUiState) {

    Column(modifier = Modifier.fillMaxSize()) {

        if (newsUiState.loading) {
            LoadingView()
        }

        if (newsUiState.error.isNullOrBlank().not()) {
            ErrorScreen(modifier = Modifier.fillMaxSize(), newsUiState.error)
        }
        if (!newsUiState.article?.status.isNullOrEmpty()) {
            val mappedData = newsUiState.article?.let { mapResponseToEntity(it) }

            if (mappedData != null) {
                NewsListScreen(newsEntity = mappedData)
            }
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier, newsEntity: List<NewsEntity>
) {
    Text(text = "NewsListScreen")

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(newsEntity) { news ->
            NewsArticleItem(
                modifier = Modifier.padding(8.dp),
                newsArticle = news
            )
        }

    }
}

@Composable
fun NewsArticleItem(modifier: Modifier, newsArticle: NewsEntity) {
    Card(modifier = modifier) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            ImageComposable(imageUrl = newsArticle.imageUrl, modifier = modifier)
            Text(text = newsArticle.description, fontSize = 16.sp)
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
            }, modifier = Modifier
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

val test = listOf(
    NewsEntity(
        0, "ktn", "prosperity", "", "2.2.2024", "", "2.2.2024", "being disciplined"
    )

)

@Preview(name = "NewsListScreen")
@Composable
private fun PreviewNewsListScreen() {

//    MainView(test)
}
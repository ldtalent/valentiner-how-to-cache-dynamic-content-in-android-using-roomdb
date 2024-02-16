package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainView(
    newsUiState: NewsViewModel.ArticleUiState,
    onNewsItemSelected: (newsItemPosition: Int) -> Unit,
) {

    Column(modifier = Modifier.fillMaxSize()) {

        if (newsUiState.loading) {
            LoadingView()
        }

        if (newsUiState.error.isNullOrBlank().not()) {
            ErrorScreen(modifier = Modifier.fillMaxSize(), newsUiState.error)
        }

        if (!newsUiState.article.isNullOrEmpty()) {
            NewsListScreen(
                newsEntity = newsUiState.article, onNewsItemSelected = onNewsItemSelected
            )
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(
    onNewsItemSelected: (newsItemPosition: Int) -> Unit,
    modifier: Modifier = Modifier,
    newsEntity: List<NewsEntity>
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(newsEntity) { index, news ->
            NewsArticleItem(
                modifier = modifier
                    .padding(8.dp)
                    .clickable {
                        onNewsItemSelected(index)
                    }, newsArticle = news
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
            Text(text = newsArticle.title, fontSize = 16.sp)
        }
    }
}


@Composable
fun LoadingView() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
fun ErrorScreen(
    modifier: Modifier, errorMsg: String
) {
    val vm = koinViewModel<NewsViewModel>()

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = errorMsg,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {
                vm.getNews()
            }, modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
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
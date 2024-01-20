package com.valentinerutto.datacacheroom.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.model.NewsArticle
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier
    ,
    viewModel: NewsViewModel = koinViewModel()
) {
    val vm = koinViewModel<NewsViewModel>()

   // val newsUiState: NewsViewModel.ArticleUiState<List<NewsEntity>> by viewModel.state


    Box(modifier) {
        Text(text = "NewsListScreen")
    }
}

@Composable
fun NewsArticleItem(modifier: Modifier, newsArticle: NewsArticle) {
    Card(modifier = modifier) {
        Column {
            ImageComposable(imageUrl = newsArticle.imageUrl, modifier = modifier)
            Text(text = newsArticle.content, style = MaterialTheme.typography.displayMedium)
        }
    }

}

@Preview(name = "NewsListScreen")
@Composable
private fun PreviewNewsListScreen() {
    NewsListScreen()
}
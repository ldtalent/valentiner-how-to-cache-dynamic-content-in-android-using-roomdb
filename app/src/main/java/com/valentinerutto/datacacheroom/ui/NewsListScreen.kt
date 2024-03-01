package com.valentinerutto.datacacheroom.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valentinerutto.datacacheroom.NewsViewModel
import com.valentinerutto.datacacheroom.R
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.ui.navigation.NavigationItem
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
            ErrorScreen(newsUiState,Modifier.fillMaxSize())
        }

        if (!newsUiState.article.isNullOrEmpty()) {
            NewsListScreen(
                newsEntity = newsUiState.article, onNewsItemSelected = onNewsItemSelected
            )
        }
    }

}


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
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = stringResource(R.string.author) + newsArticle.author,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            Text(
                text = stringResource(R.string.date) + newsArticle.publishedAt,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.padding(1.dp))
            ImageComposable(imageUrl = newsArticle.imageUrl, modifier = modifier)
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = newsArticle.title, fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                text = stringResource(R.string.read_more),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.padding(1.dp))

        }
    }
}

@Composable
fun openUrl(sourceUrl: String) {
    val context = LocalContext.current
    val urlIntent = Intent(
        Intent.ACTION_VIEW, Uri.parse(sourceUrl)
    )
    context.startActivity(urlIntent)
}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = lightColorScheme().tertiary,
            trackColor = darkColorScheme().tertiary,
        )
    }
}

@Composable
fun ErrorScreen(newsUiState: NewsViewModel.ArticleUiState,modifier:Modifier) {

    val vm = koinViewModel<NewsViewModel>()

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Text(
            text = newsUiState.error,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {
                vm.getNews()
            }, modifier = Modifier.padding(4.dp)
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
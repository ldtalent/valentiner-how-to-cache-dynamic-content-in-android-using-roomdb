package com.valentinerutto.datacacheroom

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.valentinerutto.datacacheroom.data.NewsRepository
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import kotlinx.coroutines.flow.onEach

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _state = mutableStateOf(ArticleUiState())
    val state: State<ArticleUiState> = _state
    suspend fun getNews() {
        newsRepository.getBreakingNews().onEach {

            when (it) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(loading = true, article = emptyList())
                }

                is Resource.Success ->{}
                is Resource.Error ->{}
            }
        }
    }

    data class ArticleUiState(
        val loading: Boolean = false,
        val article: List<NewsEntity> = emptyList()
    )
}
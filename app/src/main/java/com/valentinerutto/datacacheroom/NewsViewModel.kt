package com.valentinerutto.datacacheroom

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.valentinerutto.datacacheroom.data.NewsRepository
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _state = mutableStateOf(ArticleUiState())
    val state: State<ArticleUiState> = _state
    suspend fun getNews() {

        newsRepository.getBreakingNews().collect {

            when (it) {
                is Resource.Loading -> {
                    _state.value = ArticleUiState(loading = true)
                }

                is Resource.Success ->{
                    _state.value = ArticleUiState(article = it.data)
                }
                is Resource.Error ->{ArticleUiState(error = it.errorMessage)}
            }
        }
    }

    data class ArticleUiState(
        val loading: Boolean = false,
        val article: List<NewsEntity> = emptyList(),
        val error:String =""
    )
}
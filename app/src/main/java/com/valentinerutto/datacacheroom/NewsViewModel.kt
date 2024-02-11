package com.valentinerutto.datacacheroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.datacacheroom.data.NewsRepository
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _state = MutableStateFlow(ArticleUiState(loading = true))
    val state: StateFlow<ArticleUiState> = _state.asStateFlow()

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {

        newsRepository.getBreakingNews().collect {

            when (it) {
                is Resource.Loading -> {

                    setState {
                        copy(loading = true)
                    }
                }

                is Resource.Success -> {
                    setState {
                        copy(loading = false, article = it.data)
                    }
                }

                is Resource.Error -> {
                    setState {
                        copy(loading = false, error = it.errorMessage)
                    }
                }
            }
        }
    }

    private fun setState(stateReducer: ArticleUiState.() -> ArticleUiState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

    data class ArticleUiState(
        val loading: Boolean = false,
        val article: List<NewsEntity> = emptyList(),
        val error: String = ""
    )
}
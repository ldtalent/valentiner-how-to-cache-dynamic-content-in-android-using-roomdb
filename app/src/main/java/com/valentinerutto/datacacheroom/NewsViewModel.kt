package com.valentinerutto.datacacheroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.datacacheroom.data.NewsRepository
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

//     var _state by mutableStateOf(ArticleUiState())
//     private set

//    private val _state = mutableStateOf(ArticleUiState())
//    val state: State<ArticleUiState> = _state

    private val _state = MutableStateFlow(ArticleUiState(loading = true))
    val state: StateFlow<ArticleUiState> = _state.asStateFlow()
init {
    getNews()
}

     fun getNews() = viewModelScope.launch {

        newsRepository.getBreakingNews().collect {

            when (it) {
                is Resource.Loading -> {
                    //  _state = ArticleUiState(loading = true)
                    setState {
                        copy(loading = true)
                    }
                }

                is Resource.Success -> {
                    //   _state = ArticleUiState(article = it.data)
                    setState {
                        copy(loading = true, article = it.data)
                    }
                }

                is Resource.Error -> {
                    // _state =   ArticleUiState(error = it.errorMessage)
                    setState {
                        copy(loading = true, error = it.errorMessage)
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
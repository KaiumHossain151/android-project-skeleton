package ai.retail.myapp.viewmodels

import ai.retail.myapp.domain.repositories.HomeRepository
import ai.retail.myapp.network.models.Post
import ai.retail.myapp.utils.ApiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository): ViewModel(){

    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow : StateFlow<ApiState> = postStateFlow



    fun getPosts() : Flow<List<Post>> = flow {
        emit(homeRepository.getPost())
    }.flowOn(Dispatchers.IO)

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        getPosts().catch { e->
            postStateFlow.value = ApiState.Error(e)
        }.collect { data ->
            postStateFlow.value = ApiState.Success(data)
        }
    }



}

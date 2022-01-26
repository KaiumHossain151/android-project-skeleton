package ai.retail.myapp.network.interfaces.impl


import ai.retail.myapp.network.interfaces.ApiService
import ai.retail.myapp.network.models.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getPost(): List<Post> = apiService.getPosts()
}
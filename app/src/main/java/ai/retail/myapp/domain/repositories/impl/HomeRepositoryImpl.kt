package ai.retail.myapp.domain.repositories.impl

import ai.retail.myapp.data.DataManager
import ai.retail.myapp.domain.repositories.HomeRepository
import ai.retail.myapp.network.interfaces.ApiService
import ai.retail.myapp.network.models.Post


class HomeRepositoryImpl constructor(val dataManager: DataManager, val apiService: ApiService):
    HomeRepository {
    override suspend fun getPost(): List<Post> = apiService.getPosts()
}
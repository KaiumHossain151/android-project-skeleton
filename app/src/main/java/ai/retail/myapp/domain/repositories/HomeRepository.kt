package ai.retail.myapp.domain.repositories

import ai.retail.myapp.network.models.Post


interface HomeRepository {
    suspend fun getPost() : List<Post>
}
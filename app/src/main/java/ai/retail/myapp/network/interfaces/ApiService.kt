package ai.retail.myapp.network.interfaces

import ai.retail.myapp.network.models.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts() : List<Post>
}
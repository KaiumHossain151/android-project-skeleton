package ai.retail.myapp.dbhelper

import ai.retail.myapp.network.models.Post
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromComplainString(value: String): List<Post> {
        val listType = object : TypeToken<List<Post>>() {}.type
        return Gson().fromJson<List<Post>>(value, listType)
    }

    @TypeConverter
    fun fromComplainArrayList(list: List<Post>): String {
        val listType = object : TypeToken<List<Post>>() {}.type
        return Gson().toJson(list, listType)
    }

}
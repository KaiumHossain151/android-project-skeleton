package ai.retail.myapp.network

import ai.retail.myapp.di.ApplicationScope
import ai.retail.myapp.network.ApiConstants.MULTIPART_FORM_DATA
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@ApplicationScope
class CustomAuthenticationClient {
    val mGson = setGson()
    val client = OkHttpClient.Builder().build()
    private var mRetrofitDataClient: Retrofit = setDataClient()
    private fun setGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    fun setDataClient(): Retrofit {
        if (mRetrofitDataClient == null) {
            mRetrofitDataClient = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_DATA_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson)) //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofitDataClient
    }

    fun getGson(): Gson {
        return mGson
    }

    fun getRetrofitDataClient(): Retrofit {
        return mRetrofitDataClient
    }

    companion object {
        const val BASE_DATA_URL = "https://us-central1-bkashbiponon.cloudfunctions.net/"
        private const val URL_EXTENSION = "api/"
        fun createRequestBody(data: String): RequestBody {
            return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), data
            )
        }
    }

}
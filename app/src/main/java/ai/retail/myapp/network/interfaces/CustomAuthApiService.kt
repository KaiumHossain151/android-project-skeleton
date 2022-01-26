package ai.retail.myapp.network.interfaces

import ai.retail.myapp.domain.auth.AuthResponse
import ai.retail.myapp.domain.auth.LoginUserPassword
import ai.retail.myapp.domain.auth.RequestForOTP
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomAuthApiService {

    @POST("authenticate")
    fun login(@Body userPassword: LoginUserPassword?): Call<AuthResponse>

    @POST("sendOTP")
    fun sendOTP(@Body requestForOTP: RequestForOTP?): Call<AuthResponse>
}
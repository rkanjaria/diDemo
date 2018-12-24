package android.test.com.didemo.services

import android.test.com.didemo.models.UserResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("api")
    fun getUsers(@Query("results") size: Int = 10): Call<UserResult>
}

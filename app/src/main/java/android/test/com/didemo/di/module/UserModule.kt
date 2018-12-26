package android.test.com.didemo.di.module

import android.test.com.didemo.services.UserApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class UserModule {

    @Provides
    fun getUserApiService(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)

    @Provides
    fun getRetroClient(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl("https://randomuser.me/")
                    .build()


    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    fun gsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)
}


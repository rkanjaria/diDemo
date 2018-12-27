package android.test.com.didemo.di.module

import android.test.com.didemo.services.UserApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class UserModule {

    @Singleton
    @Provides
    fun getUserApiService(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun getRetroClient(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl("https://randomuser.me/")
                    .build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun gsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)
}


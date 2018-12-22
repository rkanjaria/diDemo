package android.test.com.didemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.test.com.didemo.adapters.UserAdapter
import android.test.com.didemo.services.UserApiService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        populateUsers()
    }

    private fun populateUsers() {
        val randomUsersCall = getUserService()
    }

    private fun getUserService(): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    private fun initViews() {
        userRecyclerview.setHasFixedSize(true)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
    }
}

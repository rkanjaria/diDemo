package android.test.com.didemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.test.com.didemo.R
import android.test.com.didemo.adapters.UserAdapter
import android.test.com.didemo.models.UserResult
import android.test.com.didemo.services.UserApiService
import android.view.View
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private val mAdapter: UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        val cacheFile = File(cacheDir, "HttpCache")
        cacheFile.mkdirs()

        val cache = Cache(cacheFile, 10 * 1000 * 1000)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
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
        progressBar.visibility = View.VISIBLE
        getUserService()
                .getUsers(30)
                .enqueue(object : Callback<UserResult> {
                    override fun onFailure(call: Call<UserResult>, t: Throwable) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                        progressBar.visibility = View.GONE
                        if (response.isSuccessful) {
                            mAdapter.submitList(response.body()?.userList)
                        } else {
                            Toast.makeText(baseContext, response.message(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

    private fun getUserService(): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    private fun initViews() {
        userRecyclerview.setHasFixedSize(true)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.adapter = mAdapter
    }
}

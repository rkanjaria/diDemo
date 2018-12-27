package android.test.com.didemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.test.com.didemo.R
import android.test.com.didemo.adapters.UserAdapter
import android.test.com.didemo.application.UserApplication
import android.test.com.didemo.models.UserResult
import android.test.com.didemo.services.UserApiService
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mAdapter: UserAdapter
    @Inject
    lateinit var userApiService: UserApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(MainActivityModule())
                .userComponent(UserApplication.get(this).getUserAplicationComponent())
                .build()

        mainActivityComponent.injectMainActivity(this)
        initViews()
        populateUsers()
    }

    private fun populateUsers() {
        progressBar.visibility = View.VISIBLE
        userApiService
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

    private fun initViews() {
        userRecyclerview.setHasFixedSize(true)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.adapter = mAdapter
    }
}

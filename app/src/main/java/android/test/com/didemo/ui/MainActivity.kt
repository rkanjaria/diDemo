package android.test.com.didemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.test.com.didemo.R
import android.test.com.didemo.adapters.UserAdapter
import android.test.com.didemo.services.UserApiService
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter: UserAdapter = UserAdapter()
    private lateinit var userApiService: UserApiService
    private lateinit var glide: Glide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

       // val daggerUserComponent = userApiService.

        populateUsers()
    }

    private fun populateUsers() {
        progressBar.visibility = View.VISIBLE
        /*getUserService()
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
                })*/
    }

    private fun initViews() {
        userRecyclerview.setHasFixedSize(true)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.adapter = mAdapter
    }
}

package android.test.com.didemo.models

import com.google.gson.annotations.SerializedName

data class UserResult(@SerializedName("results") val userList: List<User>?)

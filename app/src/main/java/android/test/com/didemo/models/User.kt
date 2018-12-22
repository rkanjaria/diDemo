package android.test.com.didemo.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("name") var id: String? = null,
        @SerializedName("name") var name: String? = null)

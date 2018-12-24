package android.test.com.didemo.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("gender") var gender: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("phone") var phone: String?,
        @SerializedName("cell") var cell: String?,
        @SerializedName("nat") var nat: String?,
        @SerializedName("name") var name: Name?,
        @SerializedName("location") var location: Location?,
        @SerializedName("dob") var dob: Dob?,
        @SerializedName("picture") var picture: Picture?
)

data class Name(
        @SerializedName("title") var title: String?,
        @SerializedName("first") var first: String?,
        @SerializedName("last") var last: String?
)

data class Location(
        @SerializedName("street") var street: String?,
        @SerializedName("city") var city: String?,
        @SerializedName("state") var state: String?,
        @SerializedName("postcode") var postcode: String?
)

data class Dob(
        @SerializedName("date") var date: String?,
        @SerializedName("age") var age: Int
)

data class Picture(
        @SerializedName("large") var large: String?,
        @SerializedName("medium") var medium: String?,
        @SerializedName("thumbnail") var thumbnail: String?
)

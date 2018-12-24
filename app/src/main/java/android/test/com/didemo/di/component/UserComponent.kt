package android.test.com.didemo.di.component

import android.test.com.didemo.services.UserApiService
import com.bumptech.glide.RequestManager
import dagger.Component

@Component
interface UserComponent {
    fun getUserApiService(): UserApiService
    fun getGlide(): RequestManager
}
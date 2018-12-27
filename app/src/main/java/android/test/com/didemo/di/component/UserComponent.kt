package android.test.com.didemo.di.component

import android.test.com.didemo.di.module.GlideModule
import android.test.com.didemo.di.module.UserModule
import android.test.com.didemo.services.UserApiService
import com.bumptech.glide.RequestManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, GlideModule::class])
interface UserComponent {
    fun getUserApiService(): UserApiService
    fun getGlide(): RequestManager
}
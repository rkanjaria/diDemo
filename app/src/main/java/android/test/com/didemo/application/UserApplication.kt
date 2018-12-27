package android.test.com.didemo.application

import android.app.Activity
import android.app.Application
import android.test.com.didemo.di.component.DaggerUserComponent
import android.test.com.didemo.di.component.UserComponent
import android.test.com.didemo.di.module.ContextModule

class UserApplication : Application() {

    private lateinit var userApplicationComponent: UserComponent
    companion object {
        fun get(activity: Activity): UserApplication = activity.application as UserApplication
    }

    override fun onCreate() {
        super.onCreate()
        userApplicationComponent = DaggerUserComponent.builder().contextModule(ContextModule(this)).build()
    }

    fun getUserAplicationComponent() = userApplicationComponent
}
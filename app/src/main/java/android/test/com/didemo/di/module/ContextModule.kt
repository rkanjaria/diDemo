package android.test.com.didemo.di.module

import android.content.Context
import android.test.com.didemo.di.anotations.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(val context: Context) {

    @ApplicationContext
    @Singleton
    @Provides
    fun getAppContext() = context.applicationContext
}
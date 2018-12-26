package android.test.com.didemo.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    fun getAppContext() = context.applicationContext
}
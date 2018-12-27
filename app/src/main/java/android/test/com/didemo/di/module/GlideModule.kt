package android.test.com.didemo.di.module

import android.content.Context
import android.test.com.didemo.di.anotations.ApplicationContext
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class GlideModule {
    @Singleton
    @Provides
    fun getGlide(@ApplicationContext context: Context) = Glide.with(context)
}
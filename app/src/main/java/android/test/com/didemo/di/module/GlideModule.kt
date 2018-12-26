package android.test.com.didemo.di.module

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module

@Module(includes = [ContextModule::class])
class GlideModule {
    fun getGlide(context: Context) = Glide.with(context)
}
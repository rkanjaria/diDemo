package android.test.com.didemo.ui

import android.test.com.didemo.adapters.UserAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun getUserAdapter(glide: RequestManager) = UserAdapter(glide)

}
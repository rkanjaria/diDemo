package android.test.com.didemo.di.module

import android.content.Context
import android.test.com.didemo.di.anotations.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class OkHttpClientModule {

    @Singleton
    @Provides
    fun getOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient().newBuilder()
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()

    @Singleton
    @Provides
    fun getCache(cacheFile: File) = Cache(cacheFile, 10 * 1000 * 1000)

    @Singleton
    @Provides
    fun getFile(@ApplicationContext context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Singleton
    @Provides
    fun getHttpLogginInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
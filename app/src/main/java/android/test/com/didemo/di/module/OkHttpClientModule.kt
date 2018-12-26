package android.test.com.didemo.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module(includes = [ContextModule::class])
class OkHttpClientModule {

    @Provides
    fun getOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient().newBuilder()
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()

    @Provides
    fun getCache(cacheFile: File) = Cache(cacheFile, 10 * 1000 * 1000)

    @Provides
    fun getFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Provides
    fun getHttpLogginInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
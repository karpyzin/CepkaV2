package ru.karpyzin.cepka.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.karpyzin.cepka.BuildConfig
import ru.karpyzin.data.AppConfiguration
import ru.karpyzin.data.inteceptors.DeviceInterceptor
import timber.log.Timber
import java.io.File
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        deviceInterceptor: DeviceInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpLoggingLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor(deviceInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply { level = httpLoggingLevel })
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(cache)

        // Доступ к запрещенным ресурсам
        val trustManager = object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                // Empty
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                // Empty
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }
        }

        val trustAllCerts = arrayOf(trustManager)

        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            okHttpBuilder.sslSocketFactory(sslContext.socketFactory, trustManager)
        } catch (e: Exception) {
            Timber.e("Error install ssl")
        }
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        appConfiguration: AppConfiguration
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(appConfiguration.baseHost)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()
}
package ru.karpyzin.data.inteceptors

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import ru.karpyzin.data.ext.getUserAgent
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    companion object {
        private const val CACHE_AGE = 60 * 60 * 2 // seconds + minutes + hours
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val timestamp = (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())).toString()

        val newUrl = originalRequest.url.toString()
            .replace("%26", "&")
            .replace("%3D", "=")

        val requestBuilder = originalRequest.newBuilder()
            .url(newUrl)
            .addHeader("Accept", "*/*")
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent", context.getUserAgent())
            .addHeader("X-Auth-Timestamp", timestamp)
            .addHeader("Auth", "todom")
            .addHeader("Cache-Control", "public, max-age=$CACHE_AGE")
            .method(originalRequest.method, originalRequest.body)

        return chain.proceed(requestBuilder.build())
    }
}
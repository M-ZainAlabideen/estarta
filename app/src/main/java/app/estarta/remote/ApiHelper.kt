package app.estarta.remote

import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient


class ApiHelper {
    companion object {
        fun <T> getAPI(baseUrl: String, apiClass: Class<T>): T {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(loggingInterceptor)
            okHttpClient.readTimeout(10, TimeUnit.SECONDS)
            okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
            val gson = GsonBuilder().create()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(apiClass)
        }
    }
}
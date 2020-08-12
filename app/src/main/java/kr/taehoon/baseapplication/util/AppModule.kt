package kr.taehoon.baseapplication.util

import com.google.gson.GsonBuilder
import kr.taehoon.baseapplication.MainViewModel
import kr.taehoon.baseapplication.base.BaseViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * DI Module 생성
 */

val apiModule: Module = module {

    single{
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }

    single{
        val gson = GsonBuilder().setLenient().create()
        return@single Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonBuilder().run { GsonConverterFactory.create(gson) })
            .baseUrl("https://base.com")
            .client(get(named("OkHttpClient")))
            .build()
    }

    //JWT 인증을 활용한 OkhttpCLient 생
    single(named("OkHttpClient")) {
        val headerInterceptor: Interceptor =
            get(named("header"))

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return@single OkHttpClient().newBuilder()
            .connectTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    }

    single(named("header")) {
        return@single Interceptor { chain ->
            chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "test" //JWT
                )
                .build().let {
                    val response = chain.proceed(it)
                    response
                }
        }
    }
}

val viewModelModule: Module = module {
    viewModel {
        MainViewModel()
    }
}
val appModules = listOf(apiModule, viewModelModule)
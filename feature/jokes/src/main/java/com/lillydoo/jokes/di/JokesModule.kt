package com.lillydoo.jokes.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.lillydoo.jokes.BuildConfig
import com.lillydoo.jokes.data.JokesListRepository
import com.lillydoo.jokes.data.source.remote.JokesListApi
import com.lillydoo.jokes.data.source.remote.JokesListRemoteDatasource
import com.lillydoo.jokes.domain.JokesMapper
import com.lillydoo.jokes.interactors.GetJokesUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


lateinit var idlingResource: OkHttp3IdlingResource

@Module
@InstallIn(SingletonComponent::class)
class JokesModule {


    @Provides
    fun jokesListApi(retrofit: Retrofit): JokesListApi =
        retrofit.create(JokesListApi::class.java)

    @Provides
    fun retrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        idlingResource = OkHttp3IdlingResource.create("okhttp", client)


        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)


        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }

        return okHttpClient.build()
    }


    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("LillyDoo - JokesAPP", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    fun getJokesListUseCase(repository: JokesListRepository) = GetJokesUseCase(repository)

    @Provides
    fun jokesListRepository(
        datasource: JokesListRemoteDatasource,
        mapper: JokesMapper
    ): JokesListRepository = JokesListRepository(datasource, mapper)


    @Provides
    fun jokesListRemoteDatasource(api: JokesListApi): JokesListRemoteDatasource =
        JokesListRemoteDatasource(api)

    @Provides
    fun jokesMapper(): JokesMapper {
        return JokesMapper()
    }

}
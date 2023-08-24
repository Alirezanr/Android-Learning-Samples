package com.example.composeapplication.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.composeapplication.BuildConfig
import com.example.composeapplication.BuildConfig.DEBUG
import com.example.composeapplication.data.local.BeerDatabase
import com.example.composeapplication.data.local.model.BeerEntity
import com.example.composeapplication.data.remote.BeerApi
import com.example.composeapplication.data.util.BeerRemoteMediator
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBeerDatabase(@ApplicationContext context: Context): BeerDatabase =
        Room.databaseBuilder(context, BeerDatabase::class.java, "beer_db")
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BeerApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providesBeerApi(retrofit: Retrofit): BeerApi =
        retrofit.create()


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesBeerPager(
        beerDb: BeerDatabase,
        beerApi: BeerApi,
    ): Pager<Int, BeerEntity> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )


}
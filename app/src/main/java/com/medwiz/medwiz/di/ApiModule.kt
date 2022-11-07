package com.medwiz.medwiz.di

import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.repository.auth.AuthRepoInterface
import com.medwiz.medwiz.repository.auth.AuthRepository
import com.medwiz.medwiz.util.UtilConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
//OkHttpClient for filter logs
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UtilConstants.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    //Auth Start..

    @Provides
    @Singleton
    fun provideAuth(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
    @Singleton
    @Provides
    fun provideAuthRepository( api: AuthApi) =
        AuthRepository(api) as AuthRepoInterface

}
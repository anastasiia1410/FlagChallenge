package com.example.flagchallenge.di

import com.example.flagchallenge.core.App
import com.example.flagchallenge.data.network.Api
import com.example.flagchallenge.data.network.NetworkRepository
import com.example.flagchallenge.data.network.NetworkRepositoryImpl
import com.example.flagchallenge.ui.quiz.QuizViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val dataModule = module {
    single { GsonBuilder().serializeNulls().create() }
    single {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://countries-game-eta.vercel.app/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single { get<Retrofit>().create(Api::class.java) }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
}

private val viewModelModule = module {
    viewModel { QuizViewModel(get()) }

}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(dataModule, viewModelModule)
    }
}
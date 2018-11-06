package com.airbnb.mvrx.sample.core

import android.app.Application


class MvRxApplication : Application() {

//    private val dadJokeServiceModule : Module = applicationContext {
//        bean {
//            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//            val retrofit = Retrofit.Builder()
//                    .baseUrl("https://icanhazdadjoke.com/")
//                    .addConverterFactory(MoshiConverterFactory.create(moshi))
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build()
//            retrofit.create(DadJokeService::class.java)
//        }
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        startKoin(this, listOf(dadJokeServiceModule))
//    }
}
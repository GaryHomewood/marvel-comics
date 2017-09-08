package uk.co.garyhomewood.marvelcomics.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.garyhomewood.marvelcomics.api.ComicsService

/**
 * Created by homewoodg on 31/08/2017.
 */
class Injector {

    companion object {

        private val baseUrl: String = "https://gateway.marvel.com/v1/public/"

        fun provideComicsService(): ComicsService {

            val client = OkHttpClient.Builder()
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ComicsService::class.java)
        }
    }
}
package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.core.Constants
import com.example.cryptocurrencyapp.data.remote.CryptoCurrencyService
import com.example.cryptocurrencyapp.data.remote.CryptoCurrencyServiceImpl
import com.example.cryptocurrencyapp.data.repository.CryptoCurrencyRepositoryImpl
import com.example.cryptocurrencyapp.domain.CryptoCurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                url(Constants.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Singleton
    @Provides
    fun provideCryptoCurrencyService(httpClient: HttpClient): CryptoCurrencyService {
        return CryptoCurrencyServiceImpl(httpClient)
    }

    @Singleton
    @Provides
    fun provideCryptoCurrencyRepository(
        service: CryptoCurrencyService,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): CryptoCurrencyRepository {
        return CryptoCurrencyRepositoryImpl(service, ioDispatcher)
    }


    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
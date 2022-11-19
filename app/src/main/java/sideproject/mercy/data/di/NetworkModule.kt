package sideproject.mercy.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import sideproject.mercy.data.api.GithubApi
import sideproject.mercy.data.api.KakaoApi
import sideproject.mercy.data.api.MercyApi

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Named("kakaoHeaders")
    fun provideHeaders(): Interceptor = Interceptor {
        it.run {
            proceed(
                request().newBuilder().apply {
                    addHeader("Authorization", sideproject.mercy.BuildConfig.KAKAO_REST_API_AUTHORIZATION)
                }.build()
            )
        }
    }

    @Provides
    @Named("kakaoHttpClient")
    fun provideKakaoOkHttpClient(@Named("kakaoHeaders") headers: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(headers)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

	@Provides
	fun provideOkHttpClient(): OkHttpClient =
		OkHttpClient.Builder()
			.addInterceptor(
				HttpLoggingInterceptor().apply {
					level = HttpLoggingInterceptor.Level.BODY
				}
			)
			.build()

	private val jsonBuilder = Json {
		ignoreUnknownKeys = true
	}

	@OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideConvert(): Converter.Factory {
        return jsonBuilder.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun provideGithubApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): GithubApi {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    fun provideKakaoApi(
	    @Named("kakaoHttpClient") okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): KakaoApi {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(KakaoApi::class.java)
    }

	@Provides
	fun provideMercyApi(
		okHttpClient: OkHttpClient,
		converterFactory: Converter.Factory
	): MercyApi {
		return Retrofit.Builder()
			.baseUrl("http://api.mercymercy.ml")
			.addConverterFactory(converterFactory)
			.client(okHttpClient)
			.build()
			.create(MercyApi::class.java)
	}
}
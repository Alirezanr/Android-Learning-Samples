package com.example.composeapplication.di

import com.apollographql.apollo3.ApolloClient
import com.example.composeapplication.data.ApolloCountryClient
import com.example.composeapplication.domain.repository.CountryClient
import com.example.composeapplication.domain.use_case.GetCountriesUseCase
import com.example.composeapplication.domain.use_case.GetCountryDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()


    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient =
        ApolloCountryClient(apolloClient)


    @Provides
    @Singleton
    fun provideGetCountriesUseCase(client: CountryClient): GetCountriesUseCase =
        GetCountriesUseCase(client)

    @Provides
    @Singleton
    fun provideGetCountryUseCase(client: CountryClient): GetCountryDetailUseCase =
        GetCountryDetailUseCase(client)

}
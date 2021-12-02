package com.example.movieapp.di

import com.squareup.okhttp.OkHttpClient
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit

class NetModuleTest{

    @Rule
    val mockitoRule : MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var okHttpClient : OkHttpClient

    @Mock
    lateinit var retrofit: Retrofit
}
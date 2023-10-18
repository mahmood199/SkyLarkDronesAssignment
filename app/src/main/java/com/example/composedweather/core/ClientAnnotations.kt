package com.example.composedweather.core

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SaavnClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LastFmClient

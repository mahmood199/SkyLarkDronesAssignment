package com.example.composedweather.core.remote

import javax.inject.Qualifier


// Annotation in case multiple clients are required.

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PrimaryClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SecondaryClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TertiaryClient


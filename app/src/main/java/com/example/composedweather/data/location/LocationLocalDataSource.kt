package com.example.composedweather.data.location

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationLocalDataSource @Inject constructor(
    private val client: FusedLocationProviderClient
) {

    val request by lazy {
        LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, UPDATE_INTERVAL).apply {
            setMinUpdateDistanceMeters(1000.0f)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()
    }

    val locationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationAvailability(availability: LocationAvailability) {
                super.onLocationAvailability(availability)
                if(availability.isLocationAvailable) {
                    Log.d("LocationDataSource","Location is Available")
                } else {
                    Log.d("LocationDataSource","Location is Unavailable")
                }
            }

            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                Log.d("LocationDataSource","Location result is Unavailable")
            }
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun provideLocation(): Boolean {
        return suspendCoroutine { continuation ->
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationAvailability(availability: LocationAvailability) {
                    super.onLocationAvailability(availability)
                    if(availability.isLocationAvailable) {
                        Log.d("LocationDataSource","Location is Available")
                        continuation.resume(true)
                    } else {
                        Log.d("LocationDataSource","Location is Unavailable")
                        continuation.resume(false)
                    }
                }

                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                }
            }, null)
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Result<Location> {
        return suspendCoroutine { continuation ->
            client.lastLocation.addOnSuccessListener {
                continuation.resume(Result.success(value = it))
            }.addOnFailureListener {
                continuation.resumeWith(Result.failure(exception = Throwable(it)))
            }
        }
    }



    companion object {
        private const val UPDATE_INTERVAL = 18_00_000L
    }

}
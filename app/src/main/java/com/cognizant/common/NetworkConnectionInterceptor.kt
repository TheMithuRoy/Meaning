package com.cognizant.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.cognizant.meaning.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException(context.getString(R.string.error_no_internet))
        return chain.request().newBuilder().let { chain.proceed(it.build()) }
    }

    private fun isInternetAvailable(): Boolean {
        val cm =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nc = cm.activeNetwork ?: return false
        val an = cm.getNetworkCapabilities(nc) ?: return false

        return when {
            an.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            an.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            an.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            an.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    }
}

class NoInternetException(message: String) : IOException(message)


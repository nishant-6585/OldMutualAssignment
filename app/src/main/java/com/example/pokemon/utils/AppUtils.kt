package com.example.pokemon.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object AppUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            return (nc!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        }
        return false
    }
}
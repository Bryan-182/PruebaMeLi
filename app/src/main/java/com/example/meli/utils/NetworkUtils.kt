package com.example.meli.utils

import android.content.Context
import android.net.ConnectivityManager
import java.util.Objects

class NetworkUtils {

    companion object {

        /**
         * Comprueba si hay una conexión de red disponible en el dispositivo.
         *
         * @param context El contexto de la aplicación.
         * @return true si hay una conexión de red disponible, false de lo contrario.
         */
        fun isNetworkAvailable(context: Context): Boolean {
            return try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = Objects.requireNonNull(cm).activeNetworkInfo
                activeNetwork == null || !activeNetwork.isConnected
            } catch (e: Exception) {
                Message.logMessageException(null, e)
                true
            }
        }
    }
}
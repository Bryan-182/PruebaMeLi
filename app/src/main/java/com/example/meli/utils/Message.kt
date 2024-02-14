package com.example.meli.utils

import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.meli.R
import com.example.meli.utils.Constants.Companion.LOG_MELI
import com.google.android.material.snackbar.Snackbar

class Message {
    companion object {
        //region Exceptions
        /**
         * Muestra el mensaje de la excepción y la clase desde la que se llama
         *
         * @param method Clase desde la que se llama la excepción
         * @param exception Excepción
         */
        fun logMessageException(method: Class<*>?, exception: Exception) {
            try {
                if (method != null && method.enclosingMethod != null) Log.d(
                    LOG_MELI,
                    method.enclosingMethod?.name + " " + exception.message
                )
            } catch (e: Exception) {
                Log.d(LOG_MELI, e.message.toString())
            }
        }

        /**
         * Muestra un snackBar con la información necesaria
         *
         * @param view Vista desde la que se llama la función
         * @param text Texto que mostrará el snackBar
         */
        fun showSnackBarError(view: View, text: String) {
            val snack: Snackbar = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG
            )
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.setTextColor(ContextCompat.getColor(view.context, R.color.white))
            snack.setBackgroundTint(ContextCompat.getColor(view.context, R.color.ml_blue))
            snack.show()
        }
        //endregion
    }
}
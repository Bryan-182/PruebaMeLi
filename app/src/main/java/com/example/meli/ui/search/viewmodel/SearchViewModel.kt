package com.example.meli.ui.search.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meli.data.model.Product
import com.example.meli.data.model.SearchResponse
import com.example.meli.data.repository.ProductRepository
import com.example.meli.utils.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    /**
     * Realiza una búsqueda de productos utilizando el servicio de la API de Mercado Libre.
     * Envía una solicitud de búsqueda con la consulta especificada y gestiona las respuestas del servidor.
     * Si la respuesta es exitosa y se reciben resultados válidos, actualiza la lista de productos en el LiveData [_products].
     * Si la respuesta no es exitosa o no se reciben resultados, establece la lista de productos en nulo.
     * En caso de error durante la solicitud, muestra un mensaje de error mediante un SnackBar.
     *
     * @param query El texto especificao por el usuario.
     * @param view La vista sobre la cual mostrar el mensaje de error en caso de una falla en la solicitud.
     */
    fun searchProducts(query: String, view: View) {
        productRepository.searchProducts(query)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful && response.body()!!.results!!.isNotEmpty()) {
                        _products.value = response.body()!!.results
                    } else {
                        _products.value = null
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Message.showSnackBarError(view, t.message.toString())
                }
            })
    }
}
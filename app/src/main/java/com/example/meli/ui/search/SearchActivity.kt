package com.example.meli.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.meli.R
import com.example.meli.data.remote.MeLiApiService
import com.example.meli.data.remote.RetrofitClient
import com.example.meli.data.repository.ProductRepository
import com.example.meli.databinding.ActivitySearchBinding
import com.example.meli.ui.ResultsActivity
import com.example.meli.ui.search.viewmodel.SearchViewModel
import com.example.meli.ui.search.viewmodel.SearchViewModelFactory
import com.example.meli.utils.Constants.Companion.LIST_PRODUCTS
import com.example.meli.utils.Message
import com.example.meli.utils.NetworkUtils

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivitySearchBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            setupViewModel()
            setupUI()
            observeProducts()
        } catch (e: Exception) {
            Message.logMessageException(javaClass, e)
        }
    }

    /**
     * Inicialza el ViewModel de búsqueda [SearchViewModel] utilizando un [ProductRepository]
     * que interactúa con la API de Mercado Libre a través de un servicio Retrofit [MeLiApiService].
     */
    private fun setupViewModel() {
        val apiService = RetrofitClient.retrofit.create(MeLiApiService::class.java)
        val productRepository = ProductRepository(apiService)
        viewModel = ViewModelProvider(this, SearchViewModelFactory(productRepository)).get(
            SearchViewModel::class.java
        )
    }

    /**
     * Inicializa los elementos de la vista
     */
    private fun setupUI() {
        binding.buttonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString()
            if (NetworkUtils.isNetworkAvailable(this)) {
                Message.showSnackBarError(binding.root, getString(R.string.text_no_conectado))
            } else if(query.isEmpty()) {
                Message.showSnackBarError(binding.root, getString(R.string.text_campo_vacio))
            } else {
                viewModel.searchProducts(query, binding.root)
            }
        }
    }

    /**
     * Funcion que observa el estado del servicio y navega a la actividad de resultados
     * o muestra el error correspondiente
     */
    private fun observeProducts() {
        viewModel.products.observe(this) { products ->
            if (products != null) {
                val mainIntent = Intent(this, ResultsActivity::class.java)
                mainIntent.putParcelableArrayListExtra(LIST_PRODUCTS, ArrayList(products))
                startActivity(mainIntent)
            } else {
                Message.showSnackBarError(
                    binding.root,
                    getString(R.string.text_producto_no_encontrado)
                )
            }

        }
    }
}
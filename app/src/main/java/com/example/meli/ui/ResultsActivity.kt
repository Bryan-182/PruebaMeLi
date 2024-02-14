package com.example.meli.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meli.data.model.Product
import com.example.meli.databinding.ActivityResultsBinding
import com.example.meli.ui.adapter.ProductAdapter
import com.example.meli.utils.Constants.Companion.LIST_PRODUCTS
import com.example.meli.utils.Message

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityResultsBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            binding.recyclerViewResults.layoutManager = LinearLayoutManager(this)

            val productList = getProductList()
            val adapter = ProductAdapter(productList)
            binding.recyclerViewResults.adapter = adapter
        } catch (e: Exception) {
            Message.logMessageException(javaClass, e)
        }
    }

    /**
     * Obtiene la lista de productos enviada en el intent
     *
     * @return List<Product>
     */
    private fun getProductList(): List<Product> {
        val arrayList = intent.getParcelableArrayListExtra<Product>(LIST_PRODUCTS)
        return arrayList!!.toList()
    }
}
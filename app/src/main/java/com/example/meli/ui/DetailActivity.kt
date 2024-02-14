package com.example.meli.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.meli.R
import com.example.meli.data.model.Product
import com.example.meli.databinding.ActivityDetailBinding
import com.example.meli.utils.Constants.Companion.EXTRA_PRODUCT
import com.example.meli.utils.Message

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)

            product?.let {

                Glide.with(this)
                    .load(product.thumbnail)
                    .into(binding.imageViewProduct)

                binding.textViewProductName.text = product.title
                binding.textViewProductCondition.text = product.condition

                binding.textViewProductPrice.text = HtmlCompat.fromHtml(
                    String.format(
                        getString(R.string.text_price), product.price.toString()
                    ), HtmlCompat.FROM_HTML_MODE_COMPACT
                )

                binding.textViewProductQuantity.text = HtmlCompat.fromHtml(
                    String.format(
                        getString(R.string.text_cantidad),product.availableQuantity.toString()
                    ), HtmlCompat.FROM_HTML_MODE_COMPACT
                )

                if (product.shipping!!.freeShipping) binding.textViewProductMercadopago.text = getString(R.string.text_envio_gratis)

                binding.textViewProductMercadopago.text =
                    if (product.acceptsMercadopago) getString(R.string.text_acepta_mercado_pago) else getString(R.string.text_no_acepta_mercado_pago)

                binding.textViewProductSeller.text = HtmlCompat.fromHtml(
                    String.format(
                        getString(R.string.text_vendedor),product.seller!!.nickname
                    ), HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            }
        } catch (e: Exception) {
            Message.logMessageException(javaClass, e)
        }
    }
}

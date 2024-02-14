package com.example.meli.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meli.R
import com.example.meli.data.model.Product
import com.example.meli.ui.DetailActivity
import com.example.meli.utils.Constants.Companion.EXTRA_PRODUCT
import com.example.meli.utils.Message

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        try {
            val product = productList[position]

            Glide.with(this.context).load(product.thumbnail).into(holder.imageViewProduct)

            holder.textViewProductName.text = product.title
            holder.textViewProductPrice.text = HtmlCompat.fromHtml(
                String.format(
                    context.getString(R.string.text_price),product.price.toString()
                ), HtmlCompat.FROM_HTML_MODE_COMPACT
            )

            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_PRODUCT, product)
                }
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            Message.logMessageException(javaClass, e)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
    }
}

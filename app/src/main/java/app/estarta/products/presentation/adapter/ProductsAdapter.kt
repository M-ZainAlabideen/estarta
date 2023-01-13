package app.estarta.products.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.estarta.databinding.ItemProductBinding
import kotlin.properties.Delegates
import app.estarta.shared.data.model.Product

class ProductsAdapter(
    list: List<Product> = emptyList(),
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var list: List<Product> by Delegates.observable(list) { _, old, new ->
        DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize() = old.size

                override fun getNewListSize() = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition].price == new[newItemPosition].price

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition] == new[newItemPosition]

            }
        ).also { result ->
            result.dispatchUpdatesTo(this@ProductsAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Product>) {
        list = newList
    }

    class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            product: Product,
            onItemClick: (Product) -> Unit
        ) {
            binding.apply {
                date.text = product.getDate()
                name.text = product.name
                price.text = product.price
                container.setOnClickListener { onItemClick(product) }
            }

        }
    }
}

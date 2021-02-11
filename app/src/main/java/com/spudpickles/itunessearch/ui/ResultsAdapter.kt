package com.spudpickles.itunessearch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spudpickles.itunessearch.data.entities.ITunesItem
import com.spudpickles.itunessearch.databinding.ItemArtistBinding
import com.spudpickles.itunessearch.databinding.ItemItunesBinding

class ResultsAdapter(
    val context: Context,
    var items: List<ITunesItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_VIEW_TYPE_CATEGORY = -1
    private val ITEM_VIEW_TYPE_ITEM = -2

    private var dataItems: ArrayList<DataItem>

    init {
        val groupedList = items.groupBy { it.artistName }
        dataItems = ArrayList()

        for (i in groupedList.keys) {
            dataItems.add(DataItem.Category(i))
            for (v in groupedList.getValue(i)) {
                dataItems.add(DataItem.ResultItem(v))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_CATEGORY -> Category.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ItemViewHolder.from(context, parent)
            else -> throw ClassCastException("Unknown itemViewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val dataItem = dataItems[position] as DataItem.ResultItem
                holder.bind(dataItem.iTunesItem)
            }
            is Category -> {
                val categoryItem = dataItems[position] as DataItem.Category
                holder.bind(categoryItem.categoryName)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItems[position]) {
            is DataItem.Category -> ITEM_VIEW_TYPE_CATEGORY
            is DataItem.ResultItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ItemViewHolder(
        private var context: Context,
        private var binding: ItemItunesBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ITunesItem) {
            binding.item = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(context: Context, parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemItunesBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(context, binding)
            }
        }
    }


    class Category(private var binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryName: String) {
            binding.artistGroup = categoryName

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): Category {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemArtistBinding.inflate(layoutInflater, parent, false)

                return Category(binding)
            }
        }
    }

    sealed class DataItem {
        data class ResultItem(val iTunesItem: ITunesItem) : DataItem() {
            override val id = iTunesItem.hashCode().toLong()
        }

        data class Category(val categoryName: String) : DataItem() {
            override val id = categoryName.hashCode().toLong()
        }

        abstract val id: Long
    }
}

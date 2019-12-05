package com.submission.propertyapp.ui.property

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.propertyapp.R
import com.submission.propertyapp.model.PropertyData
import org.jetbrains.anko.*


class PropertyAdapter (
    private val propertys : MutableList<PropertyData>,
    private val listener : Listener
) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return propertys.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(propertys.get(position), listener)
    }

    interface Listener {
        fun onItemClick(property: PropertyData)
        fun onLongItemClick(property: PropertyData)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaProperty: TextView = itemView.findViewById(R.id.tv_nama_property)
        val tvHargaProperty: TextView = itemView.findViewById(R.id.tv_harga_property)
        val tvStockProperty: TextView = itemView.findViewById(R.id.tv_stock_property)
        val tvLokasiProperty: TextView = itemView.findViewById(R.id.tv_lokasi_property)
        val ivImageProperty: ImageView = itemView.findViewById(R.id.iv_property)

        fun bindModel(property: PropertyData, listener: Listener) {
            tvNamaProperty.text = property.namaProperty
            tvHargaProperty.text = property.hargaProperty
            tvStockProperty.text = property.stockProperty
            tvLokasiProperty.text = property.lokasiProperty
            Glide.with(itemView.context).load(property.imageUrl).into(ivImageProperty)
            itemView.setOnClickListener {
                listener.onItemClick(property)
            }
            itemView.setOnLongClickListener{
                listener.onLongItemClick(property)
                true
            }

        }
    }
}



package com.example.implementdaggerhiltapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.implementdaggerhiltapi.R
import com.example.implementdaggerhiltapi.model.ObjectsResponseItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class rec_adapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<rec_adapter.myViewHolder>() {
    /*
     Dynamic Nature of Data
    The dataList in your use case is populated after fetching data from the API.
    This means that when the adapter is constructed (injected), the data is not yet available.
    If dataList is included in the constructor, you would need to initialize it with some placeholder data (e.g., an empty list), which is unnecessary because the actual data is dynamic and subject to updates.
     */

    private var dataList: List<ObjectsResponseItem> = emptyList()

    fun setData(data: List<ObjectsResponseItem>) {
        this.dataList = data
        notifyDataSetChanged() // Notify the adapter about the data change
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.productsName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_view, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.name.text = dataList[position].name
    }
}
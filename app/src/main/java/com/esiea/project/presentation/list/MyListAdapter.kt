package com.esiea.project.presentation.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.esiea.project.R

class MyListAdapter(myDataSet: ArrayList<String>) : RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    private var values: ArrayList<String> = myDataSet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.MyViewHolder {
        val inflatedView = parent.inflate(R.layout.row_layout, false)
        return MyViewHolder(inflatedView)
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name: String = values[position]
        holder.txtHeader.text = name
        holder.txtHeader.setOnClickListener {  }
    }

    fun add(position: Int, item: String) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    private fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        var txtHeader: TextView = v.findViewById(R.id.firstLine)
        var txtFooter: TextView = v.findViewById(R.id.secondLine)

    }
}
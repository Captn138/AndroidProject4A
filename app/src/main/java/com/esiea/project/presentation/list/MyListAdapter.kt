package com.esiea.project.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.esiea.project.R
import com.esiea.project.data.local.models.Doctor

class MyListAdapter(myDataSet: ArrayList<Doctor>) : RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    private var values: ArrayList<Doctor> = myDataSet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflatedView = parent.inflate(R.layout.row_layout, false)
        return MyViewHolder(inflatedView)
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val doctor: Doctor = values[position]
        holder.txtHeader.text = doctor.incarnation
        holder.txtFooter.text = "ID: " + doctor.id
        holder.txtHeader.setOnClickListener {
            onClick();
        }
        holder.txtFooter.setOnClickListener {
            onClick()
        }
        holder.img.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        TODO("Not yet implemented / On Click method: get to the detailed view")
    }

    fun add(position: Int, item: Doctor) {
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
        var img: ImageView = v.findViewById(R.id.icon)

    }
}
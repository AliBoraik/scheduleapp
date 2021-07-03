package com.example.scheduleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomRecyclerAdapter(private val lessens: List<Lessen>, private val listener:OnItemC) :RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private var listData: MutableList<Lessen> = lessens as MutableList<Lessen>


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var view: View? = null

        fun bind(property: Lessen, index: Int) {
            val name: TextView? = itemView.findViewById(R.id.tvtext)
            val day: TextView? = itemView.findViewById(R.id.tvDay)
            val date: TextView? = itemView.findViewById(R.id.tvDate)

            itemView.setOnClickListener(this)
            val far: FloatingActionButton? = itemView.findViewById(R.id.fabRemove)

            name?.text = property.nameLessen
            day?.text = property.day
            date?.text = property.date

            view = itemView.findViewById(R.id.cardView)
            view?.setBackgroundColor(property.color)
            far?.setOnClickListener { deleteItem(index) }

        }


        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemC {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun getItemCount() = lessens.size

    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position], position)
    }
}
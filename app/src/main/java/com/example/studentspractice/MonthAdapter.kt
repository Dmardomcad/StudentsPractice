package com.example.studentspractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MonthAdapter(
    var months: List<Month>
) : RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    inner class MonthViewHolder(monthView: View) : RecyclerView.ViewHolder(monthView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_calendar_month, parent, false)
        return MonthViewHolder(view)
    }

    override fun getItemCount(): Int {
        return months.size
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = months[position].monthName
    }


}
package com.example.studentspractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentspractice.databinding.RowCalendarDayBinding
import com.example.studentspractice.databinding.RowCalendarMonthBinding
import java.lang.IllegalArgumentException

class CalendarAdapter (var items: MutableList<CalendarItem>,
    private val onDayClickListener: (CalendarItem.DayData) -> Unit,
    private val onDayLongClickListener: (CalendarItem.DayData) -> Unit
) : RecyclerView.Adapter<CalendarRecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarRecyclerViewHolder {
        return when (viewType) {
             R.layout.row_calendar_month -> CalendarRecyclerViewHolder.MonthViewHolder(
                 RowCalendarMonthBinding.inflate(
                     LayoutInflater.from(parent.context), parent, false
                 )
             )
            R.layout.row_calendar_day -> CalendarRecyclerViewHolder.DayViewHolder(
                RowCalendarDayBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: CalendarRecyclerViewHolder, position: Int) {
        when(holder){
            is CalendarRecyclerViewHolder.MonthViewHolder -> holder.bind(items[position] as CalendarItem.MonthData)
            is CalendarRecyclerViewHolder.DayViewHolder -> {
                val dayItem = items[position] as CalendarItem.DayData
                holder.bind(dayItem)
                holder.itemView.setOnClickListener {
                    onDayClickListener(dayItem)
                }
                holder.itemView.setOnLongClickListener {
                    onDayLongClickListener(dayItem)
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is CalendarItem.MonthData -> R.layout.row_calendar_month
            is CalendarItem.DayData -> R.layout.row_calendar_day
        }
    }

}
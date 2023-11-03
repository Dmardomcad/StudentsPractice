package com.example.studentspractice

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.studentspractice.databinding.RowCalendarDayBinding
import com.example.studentspractice.databinding.RowCalendarMonthBinding

sealed class CalendarRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root){

    class MonthViewHolder(private val binding: RowCalendarMonthBinding) : CalendarRecyclerViewHolder(binding){
        fun bind(monthData: CalendarItem.MonthData) {
            binding.tvTitle.text = monthData.nameOfMonth
        }
    }

    class DayViewHolder(private val binding: RowCalendarDayBinding) : CalendarRecyclerViewHolder(binding){
            fun bind(dayData: CalendarItem.DayData){
                binding.txtDay.text = dayData.dayName
            }
        }
    }
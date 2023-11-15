package com.example.studentspractice

import android.view.View
import androidx.core.content.ContextCompat
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

    class DayViewHolder(private val binding: RowCalendarDayBinding,private val isGridLayout: Boolean) : CalendarRecyclerViewHolder(binding){
        fun bind(dayData: CalendarItem.DayData) {
            binding.txtDay.text = dayData.dayName
            binding.txtDayState.text = dayData.state
            changeTextColor(dayData.state)
            getVisibility()
        }
        private fun getVisibility() {
            if (isGridLayout) {
                binding.txtDayState.visibility = View.GONE
            } else {
                binding.txtDayState.visibility = View.VISIBLE
            }
        }
        private fun changeTextColor(state: String) {
            val colorResId = when (state) {
                "Vacaciones" -> R.color.colorStateVacaciones
                "Formación" -> R.color.colorStateFormacion
                "Centro" -> R.color.colorStateCentro
                else -> android.R.color.black
            }
                val color = ContextCompat.getColor(binding.root.context, colorResId)
                binding.txtDayState.setTextColor(color)
                binding.txtDay.setTextColor(color)
            }
        }
    }
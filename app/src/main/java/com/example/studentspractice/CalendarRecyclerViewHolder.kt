package com.example.studentspractice

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.studentspractice.databinding.RowCalendarDayBinding
import com.example.studentspractice.databinding.RowCalendarMonthBinding

sealed class CalendarRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    class MonthViewHolder(private val binding: RowCalendarMonthBinding) :
        CalendarRecyclerViewHolder(binding) {
        fun bind(monthData: CalendarItem.MonthData) {
            binding.monthLabelName.text = monthData.nameOfMonth
        }
    }

    class DayViewHolder(
        private val binding: RowCalendarDayBinding,
        private val isGridLayout: Boolean
    ) : CalendarRecyclerViewHolder(binding) {
        fun bind(dayData: CalendarItem.DayData) {
            binding.dayLabelName.text = dayData.dayName
            binding.dayLabelState.text = dayData.state
            changeTextColor(dayData.state)
            getVisibility()
        }

        private fun getVisibility() {
            if (isGridLayout) {
                binding.dayLabelState.visibility = View.GONE
            } else {
                binding.dayLabelState.visibility = View.VISIBLE
            }
        }

        private fun changeTextColor(state: String) {
            val colorResId = when (state) {
                itemView.context.getString(R.string.day_state_vacations) -> R.color.colorStateVacaciones
                itemView.context.getString(R.string.day_state_school) -> R.color.colorStateCentro
                else -> android.R.color.black
            }
            val color = ContextCompat.getColor(binding.root.context, colorResId)
            binding.dayLabelState.setTextColor(color)
            binding.dayLabelName.setTextColor(color)
        }
    }
}
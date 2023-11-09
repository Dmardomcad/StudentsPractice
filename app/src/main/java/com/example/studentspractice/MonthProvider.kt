package com.example.studentspractice

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MonthProvider {
    companion object{
        private var dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE d", Locale("es"))

        fun getDaysForMonth(month: Int, year: Int) : List<String> {
            val daysInMonth = mutableListOf<String>()
            val daysInMonthCount = getDaysInMonth(month, year)
            val firstDay = LocalDate.of(year, month, 1)

            var currentDay = firstDay
            for(i in 1..daysInMonthCount){
                if(currentDay.dayOfWeek != DayOfWeek.SATURDAY && currentDay.dayOfWeek != DayOfWeek.SUNDAY) {
                    daysInMonth.add(currentDay.format(dateFormat))
                }
                currentDay = currentDay.plusDays(1)
            }
            return daysInMonth
        }

        private fun getDaysInMonth(month: Int, year: Int): Int {
            return when (month) {
                2 -> if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                    4, 6, 9, 11 -> 30
                else -> 31
            }
        }

        fun setDateFormatLinear() {
            dateFormat = DateTimeFormatter.ofPattern("EEEE d", Locale("es"))
        }

        fun setDateFormatGrid() {
            dateFormat = DateTimeFormatter.ofPattern("EEEEE d", Locale("es"))
        }

    }
}
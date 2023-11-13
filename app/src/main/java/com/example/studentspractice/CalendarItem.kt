package com.example.studentspractice

sealed class CalendarItem {
    data class MonthData(
        val nameOfMonth: String,
    ) : CalendarItem()
    data class DayData(
        val dayName: String,
        var state: String
    ) : CalendarItem()
}

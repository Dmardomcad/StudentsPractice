package com.example.studentspractice

sealed class CalendarItem {
    data class MonthData(
        val nameOfMonth: String,
    ) : CalendarItem()
    data class DayData(
        var dayIdentifier: String,
        val dayName: String,
        var state: String
    ) : CalendarItem()
}

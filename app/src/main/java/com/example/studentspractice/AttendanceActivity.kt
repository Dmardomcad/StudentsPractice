package com.example.studentspractice

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import java.time.LocalDate

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        setCollapsingBarTitle()
        setMonthList()

        val toggleButton = binding.fab

        toggleButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setCollapsingBarTitle(){
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        binding.toolbarLayout.title = "${titleName} ${titleSurname}"
    }

    private fun setMonthList(){
        val year = 2023
        val calendarItems = mutableListOf<CalendarItem>()
        val adapterCalendar = CalendarAdapter(calendarItems)

        binding.attendanceListMonths.adapter = adapterCalendar
        binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)

        for (month in 8..12){
            val daysOfMonth = MonthProvider.getDaysForMonth(month, year)
            val monthName = LocalDate.of(year, month, 1).month.name
            calendarItems.add(CalendarItem.MonthData(monthName))
            daysOfMonth.forEach { day ->
                calendarItems.add(CalendarItem.DayData(day, "Formación"))
            }
        }
    }

}
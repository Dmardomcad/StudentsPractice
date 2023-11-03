package com.example.studentspractice

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        setCollapsingBarTitle()
        setCalendar()
        //setMonthList()
        testCalendar()

        val toggleButton = binding.fab

        toggleButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setMonthList(){
        val monthList = mutableListOf<Month>()
        val calendar = Calendar.getInstance()
        val adapterMonth = MonthAdapter(monthList)



        for(month in Calendar.SEPTEMBER ..Calendar.DECEMBER){
            calendar.set(Calendar.MONTH, month)
            val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
            monthList.add(Month(monthName))
        }
    }

    private fun setCollapsingBarTitle(){
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        binding.toolbarLayout.title = "${titleName} ${titleSurname}"
    }

    private fun setCalendar(){
        val year = 2023
        for (month in 8..12){
            val daysOfMonth = MonthProvider.getDaysForMonth(month, year)
            val monthName = LocalDate.of(year, month, 1).month.name
            Log.d("Name of month", "${monthName}")
            daysOfMonth.forEach { day ->
                Log.d("dia","${day}")
            }
        }
    }
    private fun testCalendar(){
        val calendarItems = mutableListOf<CalendarItem>()
        val adapterCalendar = CalendarAdapter(calendarItems)

        binding.rvMonths.adapter = adapterCalendar
        binding.rvMonths.layoutManager = LinearLayoutManager(this)

        val january = CalendarItem.MonthData("January")
        val dayOne = CalendarItem.DayData("Monday 1")
        calendarItems.add(january)
        calendarItems.add(dayOne)
        val february = CalendarItem.MonthData("Febrero")
        val dayTwo = CalendarItem.DayData("Martes 2")
        calendarItems.add(february)
        calendarItems.add(dayTwo)
    }


}
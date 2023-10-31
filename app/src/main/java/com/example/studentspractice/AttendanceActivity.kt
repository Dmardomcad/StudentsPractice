package com.example.studentspractice

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
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
        setMonthList()

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

        binding.rvMonths.adapter = adapterMonth
        binding.rvMonths.layoutManager = LinearLayoutManager(this)

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
        //val lastDayCalendar = Calendar.getInstance(Locale.ENGLISH)
        //val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
        //val calendar = Calendar.getInstance(Locale.ENGLISH)

        val currentYearMonth = YearMonth.now()
        val september = YearMonth.of(currentYearMonth.year, java.time.Month.SEPTEMBER)

        val currentDate = Calendar.getInstance(Locale.ENGLISH)
        val currentDay = currentDate[Calendar.DAY_OF_MONTH]
        //val currentMonth = currentDate[Calendar.MONTH]

        //val curMonthFormatted = dateFormat.format(calendar.time)
        val curDayWeek = LocalDate.now().dayOfWeek
        //val tryingDates = LocalDate.now().monthValue-1
        val daysOfThisMonth = LocalDate.now().month
        //val nameOfOtherMonth = java.time.Month.DECEMBER.value

        Log.d("Dia","${currentDay}")
        //Log.d("Mes", "${currentMonth}")
        //Log.d("Prueba formateo","${curMonthFormatted}")
        Log.d("Prueba formateo dia", "${curDayWeek}")
        Log.d("prueba formattttt","${daysOfThisMonth}")
        Log.d("Prueba java mes", "${september}")

    }


}
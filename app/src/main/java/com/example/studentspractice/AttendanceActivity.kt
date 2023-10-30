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

        val toggleButton = binding.fab

        toggleButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

        val monthList = mutableListOf(
            Month("Septiembre"),
            Month("Octubre"),
            Month("Noviembre")
        )

        val adapterMonth = MonthAdapter(monthList)
        binding.rvMonths.adapter = adapterMonth
        binding.rvMonths.layoutManager = LinearLayoutManager(this)

    }

    private fun setCollapsingBarTitle(){
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        binding.toolbarLayout.title = "${titleName} ${titleSurname}"
    }

    private fun setCalendar(){
        //val lastDayCalendar = Calendar.getInstance(Locale.ENGLISH)
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
        val calendar = Calendar.getInstance(Locale.ENGLISH)

        val currentDate = Calendar.getInstance(Locale.ENGLISH)
        val currentDay = currentDate[Calendar.DAY_OF_MONTH]
        val currentMonth = currentDate[Calendar.MONTH]

        val curMonthFormatted = dateFormat.format(calendar.time)
        val curDayWeek = LocalDate.now().dayOfWeek

        Log.d("Dia","${currentDay}")
        Log.d("Mes", "${currentMonth}")
        Log.d("Prueba formateo","${curMonthFormatted}")
        Log.d("Prueba formateo dia", "${curDayWeek}")

    }


}
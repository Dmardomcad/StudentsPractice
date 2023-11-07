package com.example.studentspractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import java.time.LocalDate

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding
    private var isGridLayout: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        setCollapsingBarTitle()
        setMonthList()

        val toggleButton = binding.fab

        toggleButton.setOnClickListener {
            if (!isGridLayout) {
                val adapterCalendar = binding.attendanceListMonths.adapter
                val newSpanCount = 5

                binding.attendanceListMonths.layoutManager = GridLayoutManager(this, newSpanCount).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (adapterCalendar?.getItemViewType(position)) {
                                R.layout.row_calendar_month -> 5
                                R.layout.row_calendar_day -> 1
                                else -> 1
                            }
                        }
                    }
                }
                isGridLayout = true
            } else {
                binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
                isGridLayout = false
            }

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
        val adapterCalendar = CalendarAdapter(calendarItems,
            { dayItem ->
                val message = "El ${dayItem.dayName} estuvo de  ${dayItem.state}"
                showAlertDialog(message)
            },
            { dayItem -> showOptionsDialog(dayItem)}
            )

        binding.attendanceListMonths.adapter = adapterCalendar
        binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)

        for (month in 9..12){
            val daysOfMonth = MonthProvider.getDaysForMonth(month, year)
            val monthName = LocalDate.of(year, month, 1).month.name
            calendarItems.add(CalendarItem.MonthData(monthName))
            daysOfMonth.forEach { day ->
                calendarItems.add(CalendarItem.DayData(day, "Formación"))
            }
        }
    }

    private fun showAlertDialog(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
            .create()
            .show()
    }

    private fun showOptionsDialog(dayItem: CalendarItem.DayData){
        val options = arrayOf("Formación", "Vacaciones","Centro")
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Cambiar estado")
            .setItems(options) { _, which ->
                val selectedOption = options[which]
                val message = "El ${dayItem.dayName} con estado ${dayItem.state} ahora está en $selectedOption"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

}
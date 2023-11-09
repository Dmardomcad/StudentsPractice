package com.example.studentspractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import java.text.DateFormatSymbols
import java.util.Locale

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

        toggleButton.setOnClickListener {toggleLayout() }
    }

    private fun setCollapsingBarTitle(){
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        binding.toolbarLayout.title = "${titleName} ${titleSurname}"
    }

    private fun setMonthList(){
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

        val monthNames = DateFormatSymbols(Locale("es")).months

        for (month in 9..12){
            val daysOfMonth = MonthProvider.getDaysForMonth(month, 2023)
            val monthName = monthNames[month - 1]
            calendarItems.add(CalendarItem.MonthData(monthName.replaceFirstChar{ it.uppercase()}))
            if (!isGridLayout) {
                daysOfMonth.forEach { day ->
                    calendarItems.add(CalendarItem.DayData(day, getString(R.string.day_state_study)))
                }
            } else {
                daysOfMonth.forEach { day ->
                    calendarItems.add(CalendarItem.DayData(day, getString(R.string.day_state_study)))
                }
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

        builder.setTitle(getString(R.string.change_day_state))
            .setItems(options) { _, which ->
                val selectedOption = options[which]
                val message = "El ${dayItem.dayName} con estado ${dayItem.state} ahora está en $selectedOption"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    private fun toggleLayout() {
        if (!isGridLayout) {
            val adapterCalendar = binding.attendanceListMonths.adapter
            val newSpanCount = 5

            MonthProvider.setDateFormatGrid()
            isGridLayout = true
            setMonthList()

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

            binding.fab.setImageResource(R.drawable.img__attendance_screen__list_button)
        } else {
            MonthProvider.setDateFormatLinear()
            isGridLayout = false
            setMonthList()
            binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
            binding.fab.setImageResource(R.drawable.img__attendance_screen__grid_button)
        }
    }

}
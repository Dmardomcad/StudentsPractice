package com.example.studentspractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormatSymbols
import java.util.Locale

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var calendarItems: MutableList<CalendarItem>
    private lateinit var adapterCalendar: CalendarAdapter
    private var isGridLayout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setAdapter()
        setCollapsingBarTitle()
        setMonthList()

        binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
        binding.attendanceActivityClose.setOnClickListener { finish() }

        val toggleButton = binding.fab
        toggleButton.setOnClickListener {toggleLayout() }
    }

    private fun setAdapter(){
        calendarItems = mutableListOf()
        adapterCalendar = CalendarAdapter(calendarItems,
            {dayItem ->
                val message = resources.getString(R.string.alert_message, dayItem.dayName, dayItem.state)
                showAlertDialog(message)
            },
            {dayItem -> setNewDayState(dayItem)},
            isGridLayout
        )
        binding.attendanceListMonths.adapter = adapterCalendar
    }

    private fun setCollapsingBarTitle(){
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        binding.toolbarLayout.title = "${titleName} ${titleSurname}"
    }

    private fun setMonthList(){
        calendarItems.clear()

        val monthNames = DateFormatSymbols(Locale("es")).months

        for (month in 9..12){
            val daysOfMonth = MonthProvider.getDaysForMonth(month, 2023)
            val monthName = monthNames[month - 1]

            calendarItems.add(CalendarItem.MonthData(monthName.replaceFirstChar{ it.uppercase()}))

            daysOfMonth.forEach { day ->
                calendarItems.add(CalendarItem.DayData(day, getString(R.string.day_state_study)))
            }
        }
    }

    private fun showAlertDialog(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setPositiveButton(R.string.accept) { dialog, _ ->
            dialog.dismiss()
        }
            .create()
            .show()
    }

    private fun setNewDayState(dayItem: CalendarItem.DayData){
        val options : Array<String> = resources.getStringArray(R.array.options_items)
        val builder = AlertDialog.Builder(this)
        val previousState = dayItem.state

        builder.setTitle(getString(R.string.change_day_state))
            .setItems(options) { _, which ->
                val selectedOption = options[which]
                dayItem.state = selectedOption
                adapterCalendar.notifyItemChanged(calendarItems.indexOf(dayItem))
                Log.d("Comprobación indexOf","El index es : ${calendarItems.indexOf(dayItem)}")


                showUndoSnackbar(previousState, dayItem)
                Log.d("Comprobación option selected", "La opción es ${selectedOption}")
            }
            .create()
            .show()
    }

    private fun showUndoSnackbar(previousState: String, dayItem: CalendarItem.DayData) {
        val snackbar = Snackbar.make(
            binding.attendanceContainerLayout,
            R.string.changed_state,
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction(R.string.undo) {
            dayItem.state = previousState
            adapterCalendar.notifyItemChanged(calendarItems.indexOf(dayItem))
            Snackbar.make(binding.attendanceContainerLayout, R.string.undo_change, Snackbar.LENGTH_SHORT).show()
        }
        snackbar.show()
    }

    private fun toggleLayout() {
        if (!isGridLayout) {
            val newSpanCount = 5
            isGridLayout = true
            setAdapter()
            MonthProvider.setDateFormatGrid()
            setMonthList()

            binding.attendanceListMonths.layoutManager = GridLayoutManager(this, newSpanCount).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapterCalendar.getItemViewType(position)) {
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
            setAdapter()
            setMonthList()
            binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
            binding.fab.setImageResource(R.drawable.img__attendance_screen__grid_button)
        }
    }

}
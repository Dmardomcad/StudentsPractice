package com.example.studentspractice

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentspractice.databinding.ActivityAttendanceBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormatSymbols
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var calendarItems: MutableList<CalendarItem>
    private lateinit var adapterCalendar: CalendarAdapter
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private var dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE d", Locale("es"))
    private var modifiedDayStates: MutableMap<String, String> = mutableMapOf()
    private var isGridLayout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setCollapsingBarTitle()
        setMonthList()

        binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
        binding.attendanceBtnClose.setOnClickListener { finish() }
        binding.attendanceBtnToggleLayout.setOnClickListener { toggleLayout() }
    }

    private fun setAdapter() {
        calendarItems = mutableListOf()
        adapterCalendar = CalendarAdapter(
            calendarItems,
            { dayItem ->
                val message =
                    resources.getString(R.string.alert_message, dayItem.dayName, dayItem.state)
                showAlertDialog(message)
            },
            { dayItem -> setNewDayState(dayItem) },
            isGridLayout
        )
        binding.attendanceListMonths.adapter = adapterCalendar
    }

    private fun setCollapsingBarTitle() {
        val titleName = intent.getStringExtra("name")
        val titleSurname = intent.getStringExtra("surname")
        val expandedTitle = binding.attendanceLabelTitle
        appBarLayout = binding.appBar
        collapsingToolbarLayout = binding.toolbarLayout

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            if (verticalOffset.plus(totalScrollRange) == 0) {
                expandedTitle.text = "$titleName"
            } else {
                expandedTitle.text = "$titleName ${titleSurname?.substringBefore(" ")}"
            }
        }
    }

    private fun setMonthList() {
        calendarItems.clear()

        var dayId = 0
        val monthNames = DateFormatSymbols(Locale("es")).months

        for (month in 9..12) {
            val daysOfMonth = getDaysForMonth(month, 2023)
            val monthName = monthNames[month - 1]

            calendarItems.add(CalendarItem.MonthData(monthName.replaceFirstChar { it.uppercase() }))

            daysOfMonth.forEach { day ->
                dayId++
                val dayIdentifier = "${monthName}_${dayId}"
                val modifiedState = modifiedDayStates[dayIdentifier]
                if (modifiedState != null) {
                    calendarItems.add(CalendarItem.DayData(dayIdentifier, day, modifiedState))
                } else {
                    calendarItems.add(
                        CalendarItem.DayData(
                            dayIdentifier,
                            day,
                            getString(R.string.day_state_study)
                        )
                    )
                }
            }
        }
    }

    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setPositiveButton(R.string.accept) { dialog, _ ->
            dialog.dismiss()
        }
            .create()
            .show()
    }

    private fun setNewDayState(dayItem: CalendarItem.DayData) {
        val options: Array<String> = resources.getStringArray(R.array.options_items)
        val builder = AlertDialog.Builder(this)
        val previousState = dayItem.state

        builder.setTitle(getString(R.string.change_day_state))
            .setItems(options) { _, which ->
                val selectedOption = options[which]
                dayItem.state = selectedOption
                adapterCalendar.notifyItemChanged(calendarItems.indexOf(dayItem))
                modifiedDayStates[dayItem.dayIdentifier] = selectedOption
                showUndoSnackbar(previousState, dayItem)
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
            modifiedDayStates[dayItem.dayIdentifier] = previousState
            adapterCalendar.notifyItemChanged(calendarItems.indexOf(dayItem))
            Snackbar.make(
                binding.attendanceContainerLayout,
                R.string.undo_change,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        snackbar.show()
    }

    private fun toggleLayout() {
        if (!isGridLayout) {
            val newSpanCount = 5
            isGridLayout = true
            setAdapter()
            setDateFormatGrid()
            setMonthList()

            binding.attendanceListMonths.layoutManager =
                GridLayoutManager(this, newSpanCount).apply {
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

            binding.attendanceBtnToggleLayout.setImageResource(R.drawable.img__attendance_screen__list_button)
        } else {
            setDateFormatLinear()
            isGridLayout = false
            setAdapter()
            setMonthList()
            binding.attendanceListMonths.layoutManager = LinearLayoutManager(this)
            binding.attendanceBtnToggleLayout.setImageResource(R.drawable.img__attendance_screen__grid_button)
        }
    }

    private fun getDaysForMonth(month: Int, year: Int): List<String> {
        val daysInMonth = mutableListOf<String>()
        val daysInMonthCount = getDaysInMonth(month, year)
        val firstDay = LocalDate.of(year, month, 1)
        var currentDay = firstDay

        for (i in 1..daysInMonthCount) {
            if (currentDay.dayOfWeek != DayOfWeek.SATURDAY && currentDay.dayOfWeek != DayOfWeek.SUNDAY) {
                daysInMonth.add(currentDay.format(dateFormat))
            }
            currentDay = currentDay.plusDays(1)
        }
        return daysInMonth
    }

    private fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month) {
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
    }

    private fun setDateFormatLinear() {
        dateFormat = DateTimeFormatter.ofPattern("EEEE d", Locale("es"))
    }

    private fun setDateFormatGrid() {
        dateFormat = DateTimeFormatter.ofPattern("EEEEE d", Locale("es"))
    }

}
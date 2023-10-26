package com.example.studentspractice

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.studentspractice.databinding.ActivityAttendanceBinding

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        setCollapsingBarTitle()

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
}
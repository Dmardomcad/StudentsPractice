package com.example.studentspractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studentspractice.databinding.ActivityStudentsBinding
import com.google.android.material.tabs.TabLayoutMediator


class StudentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabLayout()
        binding.studentBtnCalendar.setOnClickListener { navigateToAttendance() }

    }

    private fun navigateToAttendance() {
        val intent = Intent(this, AttendanceActivity::class.java)
        startActivity(intent)
    }

    private fun setUpTabLayout() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.fragmentPager
        val adapter = StudentAdapter(this, StudentProvider.studentList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
        val students = StudentProvider.studentList
        val currentStudentEmail = students[position].email

        return currentStudentEmail.substringBefore('@')
    }
}
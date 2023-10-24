package com.example.studentspractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import com.example.studentspractice.databinding.ActivityStudentsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding: ActivityStudentsBinding
class StudentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTabLayout()

        setUpClickListeners()

    }
    private fun setUpClickListeners(){
        binding.studentImgBtnCalendar.setOnClickListener{ navigateToAttendance() }

    }
    private fun navigateToAttendance(){

    }
    private fun setTabLayout(){
        val tabLayout = binding.tabLayout
        val viewPager = binding.fragmentPager
        val adapter = StudentAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
                tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String?{
        val students = StudentProvider.studentList

        val curStudentName = students[position].name
        val curStudentSurname = students[position].surname
        return "${curStudentName}.${curStudentSurname}"
    }
}
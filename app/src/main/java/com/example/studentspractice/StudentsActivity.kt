package com.example.studentspractice

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

    }

    private fun setUpTabLayout(){
        val tabLayout = binding.tabLayout
        val viewPager = binding.fragmentPager
        val adapter = StudentAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
                tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String {
        val students = StudentProvider.studentList
        val curStudentEmail = students[position].email

        return curStudentEmail.substringBefore('@')
    }
}
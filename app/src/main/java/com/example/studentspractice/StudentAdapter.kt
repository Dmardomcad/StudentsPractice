package com.example.studentspractice

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StudentAdapter(fragmentActivity: AppCompatActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        val students = StudentProvider.studentList
        return students.size
    }

    override fun createFragment(position: Int): Fragment {
        return StudentFragment.newInstance(position)
    }
}
package com.example.studentspractice

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

val students = StudentProvider.studentList

class StudentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return students.size
        }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}
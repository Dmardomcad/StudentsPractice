package com.example.studentspractice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

val students = StudentProvider.studentList

class StudentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return students.size
        }

    override fun createFragment(position: Int): Fragment {
        return StudentFragment.newInstance(position)
    }
}
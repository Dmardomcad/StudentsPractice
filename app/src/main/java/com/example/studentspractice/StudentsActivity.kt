package com.example.studentspractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

/*        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<StudentFragment>(R.id.fragmentContainer)

                //Probando cosas
                val studentname = students[1].city
                Log.d("thename", studentname)
                //
            }
        }*/

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.fragmentPager)

        val adapter = StudentAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

    }
}
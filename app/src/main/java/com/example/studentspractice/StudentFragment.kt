package com.example.studentspractice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.studentspractice.databinding.FragmentStudentBinding


class StudentFragment : Fragment() {
    private var binding: FragmentStudentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentBinding.inflate(layoutInflater)
        return binding?.root
    }

    companion object {
        private const val POSITION_ARG = "position_arg"

        @JvmStatic
        fun newInstance(position: Int) =
            StudentFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION_ARG, position)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val studentName = students[0].name
        val studentSurname = students[0].surname
        val studentEmail = students[0].email

        binding?.studentNameContainer?.text = studentName
        binding?.studentSurnameContainer?.text = studentSurname
        binding?.studentEmailContainer?.text = studentEmail
    }
}
package com.example.studentspractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val curStudent = arguments?.getInt(POSITION_ARG)
        val students = StudentProvider.studentList

        if(curStudent != null){
            binding?.studentNameContainer?.text = students[curStudent].name
            binding?.studentSurnameContainer?.text = students[curStudent].surname
            binding?.studentEmailContainer?.text = students[curStudent].email
            binding?.studentCityContainer?.text = students[curStudent].city
            binding?.studentImgContainer?.setImageResource(students[curStudent].avatarId)
            binding?.studentSchoolContainer?.text = students[curStudent].school
            binding?.studentTutorContainer?.text = "${students[curStudent].tutor.name} ${students[curStudent].tutor.surname}"
        }
    }
}
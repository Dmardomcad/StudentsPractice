package com.example.studentspractice

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentspractice.databinding.FragmentStudentBinding


class StudentFragment : Fragment() {

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

    private var binding: FragmentStudentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentBinding.inflate(layoutInflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNavigator = binding?.studentBtnCalendar
        buttonNavigator?.setOnClickListener{
            val curStudent = arguments?.getInt(POSITION_ARG)
            val students = StudentProvider.studentList

            if(curStudent != null){
                val intent = Intent(context, AttendanceActivity::class.java)
                intent.putExtra("name",students[curStudent].name)
                intent.putExtra("surname",students[curStudent].surname)
                startActivity(intent)
            }
        }

        val currentStudent = arguments?.getInt(POSITION_ARG)
        val students = StudentProvider.studentList

        if(currentStudent != null){
            val actualStudent = students[currentStudent]
            actualStudent.apply{
                binding?.studentNameContainer?.text = name
                binding?.studentSurnameContainer?.text = surname
                binding?.studentEmailContainer?.text = email
                binding?.studentCityContainer?.text = city
                binding?.studentImgContainer?.setImageResource(avatarId)
                binding?.studentSchoolContainer?.text = school
                binding?.studentTutorContainer?.text = "${tutor.name} ${tutor.surname}"
            }
        }
    }
}
package com.example.studentspractice

class StudentProvider {
    companion object {
        val studentList = listOf<Student>(
            Student(
                "Cádiz",
                "Daniel",
                "Mariscal Dominguez",
                "daniel.mariscal@alten.es",
                R.drawable.img__student_screen_daniel,
                "IES Rafael Alberti",
                TutorProvider.tutorList[0]
            ),
            Student(
                "Badajoz",
                "Claudia",
                "Montero quijote",
                "claudia.montero@alten.es",
                R.drawable.img__student_screen__claudia,
                "IES Invent",
                TutorProvider.tutorList[0]

            ),
            Student(
                "Sevilla",
                "Salvador",
                "Rodriguez Benalmadena",
                "salvador.rodriguez@alten.es",
                R.drawable.img__student_screen__salvador,
                "Oxford",
                TutorProvider.tutorList[2]
            ),
            Student(
                "Sevilla",
                "Jesus",
                "Jimenez Santos",
                "jesus.jimenez@alten.es",
                R.drawable.img__student_screen__jesus,
                "Madeup School Name",
                TutorProvider.tutorList[1]
            )
        )
    }
}
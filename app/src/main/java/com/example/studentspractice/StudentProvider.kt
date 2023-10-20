package com.example.studentspractice

class StudentProvider {
    companion object {
        val studentList = listOf<Student>(
            Student(
                "Cádiz",
                "Daniel",
                "Mariscal Dominguez",
                "daniel.mariscal@alten.es"
            ),
            Student(
                "Badajoz",
                "Claudia",
                "Montero quijote",
                "claudia.montero@alten.es"
            ),
            Student(
                "Sevilla",
                "Salvador",
                "Rodriguez Benalmadena",
                "salvador.rodriguez@alten.es"
            ),
            Student(
                "Sevilla",
                "Jesús Miguel",
                "Jimenez Santos",
                "jesus.jimenez@alten.es"
            )
        )
    }
}
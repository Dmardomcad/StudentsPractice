package com.example.studentspractice

data class Tutor (
     val name: String,
     val surname: String,
     val email: String,
) : Person(name, surname, email)
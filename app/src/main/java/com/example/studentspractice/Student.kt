package com.example.studentspractice

data class Student(
    val city: String,
    val name: String,
    val surname: String,
    val email: String,
) : Person(name, surname, email)
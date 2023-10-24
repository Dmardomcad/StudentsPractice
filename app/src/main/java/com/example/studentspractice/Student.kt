package com.example.studentspractice

data class Student(
    val city: String,
    val name: String,
    val surname: String,
    val email: String,
    val avatarId: Int,
    val school: String,
    val tutor: Tutor
) : Person(name, surname, email)
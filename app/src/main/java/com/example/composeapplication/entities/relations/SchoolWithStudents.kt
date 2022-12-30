package com.example.composeapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.composeapplication.entities.School
import com.example.composeapplication.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)
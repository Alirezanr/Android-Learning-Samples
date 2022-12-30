package com.example.composeapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.composeapplication.entities.Student
import com.example.composeapplication.entities.Subject
import com.example.composeapplication.entities.relations.StudentSubjectCrossRef

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)
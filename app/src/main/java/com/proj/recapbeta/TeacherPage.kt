package com.proj.recapbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TeacherPage : AppCompatActivity() {

    lateinit var uploadBtn: Button
    lateinit var coursecat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacherpage)

        uploadBtn = findViewById(R.id.uploadBtn)
        coursecat = findViewById(R.id.courseCatBtn)

        uploadBtn.setOnClickListener{
            val upActyIntent = Intent(this, UploadVideo::class.java)
            startActivity(upActyIntent)
        }
        coursecat.setOnClickListener{
            val toCatIntent = Intent(this, CourseCatalog::class.java)
            startActivity(toCatIntent)
        }
    }
}
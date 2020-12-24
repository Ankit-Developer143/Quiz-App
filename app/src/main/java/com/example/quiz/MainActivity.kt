package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var et_name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Use for FullScreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn = findViewById(R.id.btn_start)
        et_name = findViewById(R.id.et_name)
        btn.setOnClickListener {
            when {
                et_name.text.toString().isEmpty() -> {
                    Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show()

                }
                else -> {
                   val intent = Intent(this,QuizQuestionActivity::class.java)
                    intent.putExtra(Constant.USER_NAME,et_name.text.toString())
                    startActivity(intent)


                }
            }
        }
    }
}
package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    var UserName= findViewById<TextView>(R.id.tv_username)
    var score = findViewById<TextView>(R.id.tv_score)
    var btn_finish = findViewById<Button>(R.id.btn_finish)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var  username= intent.getStringExtra(Constant.USER_NAME)
        UserName.text =username
        var totalQuestion = intent.getIntExtra(Constant.TOTAL_QUESTIONS,0)
        var correctAnswer = intent.getIntExtra(Constant.CORRECT_ANSWERS,0)
        score.text = "Your Score is $correctAnswer out of $totalQuestion "

        btn_finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
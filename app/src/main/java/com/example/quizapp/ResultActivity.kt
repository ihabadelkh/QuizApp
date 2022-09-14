package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvScore:TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        val score = intent.getIntExtra(Constants.SCORE, 0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        tvScore.text = "Your score $score/$totalQuestions"

        btnFinish.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })




    }
}
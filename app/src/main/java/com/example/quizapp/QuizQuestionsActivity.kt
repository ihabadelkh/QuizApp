package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mQuestionList: ArrayList<Question>? = null
    private var mCurrentPosition = 1
    private var mSelectedOptionNum = 0
    private var mUserName: String? = null
    private var score: Int = 0

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvQuestionNumber: TextView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.image_view)
        progressBar = findViewById(R.id.progressBar)
        tvQuestionNumber = findViewById(R.id.tv_question_number)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)



        mQuestionList = Constants.getQuestion()


        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()


    }


    private fun setQuestion() {
        defaultOptionsView()
        val question = mQuestionList!![mCurrentPosition - 1]
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvQuestionNumber?.text = "$mCurrentPosition/${progressBar?.max}"
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    private fun defaultOptionsView() {
        var options = ArrayList<TextView>()
        tvOptionOne?.let { options.add(0, it) }
        tvOptionTwo?.let { options.add(1, it) }
        tvOptionThree?.let { options.add(2, it) }
        tvOptionFour?.let { options.add(3, it) }
        btnSubmit?.text = "Submit"

        for (option in options) {
            option.setTextColor(Color.GRAY)
            option.background = ContextCompat.getDrawable(this, R.drawable.default_tv_bg)
        }
    }

    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionNum = selectedOptionNum

        tv.setTextColor(Color.BLACK)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selcted_tv_bg)

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }


    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.tv_option_one -> {
                tvOptionOne?.let { selectedOptionsView(it, 1) }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let { selectedOptionsView(it, 2) }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let { selectedOptionsView(it, 3) }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let { selectedOptionsView(it, 4) }
            }

            R.id.btn_submit -> {
                if (mSelectedOptionNum == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            var intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.SCORE, score)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionNum) {
                        answerView(mSelectedOptionNum, R.drawable.wrong_tv_bg)
                    } else {
                        score++
                    }
                    answerView(question!!.correctAnswer, R.drawable.correct_tv_bg)

                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnSubmit?.text = "Finish"
                    } else {
                        btnSubmit?.text = "Next"
                    }
                    mSelectedOptionNum = 0
                }


            }
        }
    }
}
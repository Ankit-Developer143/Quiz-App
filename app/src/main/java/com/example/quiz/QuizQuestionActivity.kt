package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private  var mCurrentPosition:Int = 1

    //get the Question from Constant
    private  var mQuestionList: ArrayList<Question>? = null
    private  var mSelectedOptionPosition:Int = 0
    //step 9 Check and Count Correct Answer
    private var mCorrectAnswer:Int = 0

    //Hold UserName
    private var mUserName:String? = null


    lateinit var progressBar: ProgressBar
    lateinit var tv_progress:TextView
    lateinit var tv_question:TextView
    lateinit var iv_image:ImageView
    lateinit var tv_option_one:TextView
    lateinit var tv_option_two:TextView
    lateinit var tv_option_three:TextView
    lateinit var tv_option_four:TextView
    lateinit var tv_option_five:TextView
    lateinit var btn_submit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        //Hold UserName
        mUserName = intent.getStringExtra(Constant.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)
        iv_image = findViewById(R.id.iv_image)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        tv_option_five = findViewById(R.id.tv_option_five)
        btn_submit = findViewById(R.id.btn_submit)

        //get the Question from Constant and save it inside  mQuestionList
        mQuestionList = Constant.getQuestions()
        setQuestion()
        }

    private fun setQuestion() {
        //get current postion for progress Bar
        val question:Question? = mQuestionList!![mCurrentPosition-1]

        defaultOptionView()

        //Step 8
        //change button to next to submit
        if(mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition"+'/'+ progressBar.max
        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        tv_option_five.text = question.optionFive

    }
    //Click Layout set default  step 3
    //All index value save in option
    private  fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)
        options.add(4,tv_option_five)


        //when click other option then all text layout set default
        for(option in options){
            option.setTextColor(Color.parseColor("white"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.design_default_option_border_bg)


            //when you create this make a class View.OnClickListener and implement member
            tv_option_one.setOnClickListener(this)
            tv_option_two.setOnClickListener(this)
            tv_option_three.setOnClickListener(this)
            tv_option_four.setOnClickListener(this)
            tv_option_five.setOnClickListener(this)
            btn_submit.setOnClickListener(this)
        }
    }

//Step 5
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.tv_option_five ->{
                selectedOptionView(tv_option_five,5)
            }
            //step 6
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{

                        //step 11 data  pass to result
                        val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constant.USER_NAME,mUserName)
                        intent.putExtra(Constant.CORRECT_ANSWERS,mCorrectAnswer)
                        intent.putExtra(Constant.TOTAL_QUESTIONS,mQuestionList!!.size)
                        startActivity(intent)
                    }
                    }
                }
                else{
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if (question!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_color)
                    }
                    //step 9
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.right_answer_design)

                    //Button Value Change
                    if(mCurrentPosition == mQuestionList?.size){
                        btn_submit.text ="Finish"
                    }else{
                        btn_submit.text = "GO TO NEXT QUESTION"

                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    //Check Answer correct or Not
    //Check option 6
    private fun answerView(answer:Int,drawableView:Int){
         when(answer){
             1 ->{
                 tv_option_one.background = ContextCompat.getDrawable(this,drawableView)
             }
             2 ->{
                 tv_option_two.background = ContextCompat.getDrawable(this,drawableView)
             }
             3 ->{
                 tv_option_three.background = ContextCompat.getDrawable(this,drawableView)
             }
             4 ->{
                 tv_option_four.background = ContextCompat.getDrawable(this,drawableView)
             }
             5 ->{
                 tv_option_five.background = ContextCompat.getDrawable(this,drawableView)
             }
         }

    }
    //Step 4
    //value enter step5
    private  fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionView()

        //selectedOptionNum value come from when statements and assign to mSelectedPosition
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_default_option_border_bg)

    }

}

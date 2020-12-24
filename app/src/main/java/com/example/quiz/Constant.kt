package com.example.quiz

object Constant {
    //step 10
    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_question"
    const val CORRECT_ANSWERS:String = "correct_answers"
    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val que1 = Question(1,"What country does this flag  belong to?",R.drawable.india,"India","Pakistan","Nepal","Iraq","England",1)
        questionsList.add(que1)
        val que2 = Question(2,"What country does this flag  belong to?",R.drawable.turkey,"Bhutan","England","Turkey","New Zealand","Russia",3)
        questionsList.add(que2)
        val que3 = Question(3,"What country does this flag  belong to?",R.drawable.china,"India","England","Nepal","China","Bhutan",4)
        questionsList.add(que3)
        return questionsList
    }
}
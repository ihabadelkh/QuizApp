package com.example.quizapp

object Constants {

    const val USER_NAME = "user_name"
    const val SCORE = "score"
    const val TOTAL_QUESTIONS = "total_questions"

    fun getQuestion(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val ques1 = Question(1, "What country does this flag belong to?", R.drawable.flag_of_algeria, "Egypt",
            "Jourdan", "Algeria", "Kuwait", 3)
        questionList.add(ques1)

        val ques2 = Question(2, "What country does this flag belong to?", R.drawable.flag_of_egypt, "Egypt",
            "Jourdan", "Algeria", "Saudi Arabia", 1)
        questionList.add(ques2)

        val ques3 = Question(3, "What country does this flag belong to?", R.drawable.flag_of_iraq, "Egypt",
            "Jourdan", "Algeria", "Iraq", 4)
        questionList.add(ques3)

        val ques4 = Question(4, "What country does this flag belong to?", R.drawable.flag_of_jordan, "Egypt",
            "Jourdan", "Algeria", "Kuwait", 2)
        questionList.add(ques4)

        val ques5 = Question(5, "What country does this flag belong to?", R.drawable.flag_of_saudi_arabia, "Egypt",
            "Jourdan", "Saudi Arabia", "Kuwait", 3)
        questionList.add(ques5)

        return questionList
    }
}
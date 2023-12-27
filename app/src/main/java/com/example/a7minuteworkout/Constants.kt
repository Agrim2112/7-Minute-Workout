package com.example.a7minuteworkout

import android.support.v4.os.IResultReceiver.Default

object Constants {
    fun DefaultExerciseList():ArrayList<ExerciseModel>
    {
        val ExerciseList=ArrayList<ExerciseModel>()

        val jumpingjacks=ExerciseModel(1,"JUMPING JACKS",R.drawable.img2,"Jumping Jacks",false,false)
        ExerciseList.add(jumpingjacks)

        val plank=ExerciseModel(2,"PLANK",R.drawable.img,"Plank",false,false)
        ExerciseList.add(plank)

        val situps=ExerciseModel(3,"SITUPS",R.drawable.img,"Situps",false,false)
        ExerciseList.add(situps)

        val bodycrunches=ExerciseModel(4,"BODY CRUNCHES",R.drawable.img,"Body Crunches",false,false)
        ExerciseList.add(bodycrunches)

        val toereaches=ExerciseModel(5,"TOE REACHES",R.drawable.img,"Toe Reaches",false,false)
        ExerciseList.add(toereaches)

        val russiantwists=ExerciseModel(6,"RUSSIAN TWISTS",R.drawable.img,"Russian Twists",false,false)
        ExerciseList.add(russiantwists)

        return ExerciseList
    }

}
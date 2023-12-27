package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.databinding.ActivityFinishBinding


private var binding : ActivityFinishBinding?=null

class Finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFinish?.setOnClickListener()
        {
            val intent=Intent(this,MainActivity::class.java)
            startActivity((intent))
        }
    }
}
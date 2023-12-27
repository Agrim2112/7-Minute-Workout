package com.example.a7minuteworkout

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import com.example.a7minuteworkout.databinding.ActivityBmiresult2Binding

class BMIresult : AppCompatActivity() {
    private var binding: ActivityBmiresult2Binding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiresult2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        val receivedIntent = intent
        val receivedValue = receivedIntent.getDoubleExtra("BMI", 25.0)
        binding?.tvBMI?.text="$receivedValue"

        if(receivedValue<18.5)
        {
            binding?.tvBMIclass?.text="Underweight"
            binding?.tvBMIclass?.setTextColor(Color.parseColor("#FFE8E40D"))
            binding?.tvBMIhelp?.text="Your BMI is in underweight range. Focus on building lean muscle mass through strength training exercises. Incorporate protein-rich foods to support muscle growth and overall health."
        }
        else if(receivedValue<25)
        {
            binding?.tvBMIclass?.text="Normal"
            binding?.tvBMIhelp?.text="Congratulations! Your BMI is in normal range. Continue with a well-rounded fitness routine that includes cardio for heart health and strength training for muscle tone."
        }
        else if(receivedValue<30)
        {
            binding?.tvBMIclass?.text="Overweight"
            binding?.tvBMIclass?.setTextColor(Color.parseColor("#FFE8730D"))
            binding?.tvBMIhelp?.text="Your BMI is in the overweight range, but no worries! Engage in regular cardiovascular exercises to burn calories and incorporate strength training to boost metabolism."
        }
        else
        {
            binding?.tvBMIclass?.text="Obesity"
            binding?.tvBMIclass?.setTextColor(Color.parseColor("#FFFF3300"))
            binding?.tvBMIhelp?.text="Your BMI is in the obesity range. Prioritize high-intensity workouts to burn fat and build strength. Consider consulting a fitness professional for a personalized plan and focus on a nutrient-dense diet."
        }

        binding?.ivbtn?.setOnClickListener()
        {
            val intent = Intent(this,BMI::class.java)
            startActivity(intent)
        }
    }
}
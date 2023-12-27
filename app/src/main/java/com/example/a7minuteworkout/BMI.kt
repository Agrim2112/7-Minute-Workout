package com.example.a7minuteworkout

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import com.example.a7minuteworkout.databinding.ActivityMainBinding

class BMI : AppCompatActivity() {
    private var binding: ActivityBmiBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        var k:Int=0

        binding?.llMale?.setOnClickListener()
        {
            binding?.llMale?.background = ContextCompat.getDrawable(this, R.drawable.bg2)
            binding?.llFemale?.background = ContextCompat.getDrawable(this, R.drawable.bg)
            k=1
        }
        binding?.llFemale?.setOnClickListener()
        {
            binding?.llFemale?.background = ContextCompat.getDrawable(this, R.drawable.bg2)
            binding?.llMale?.background = ContextCompat.getDrawable(this, R.drawable.bg)
            k=1
        }

        binding?.seekbarforheight?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding?.currentheight?.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding?.seekbarforweight?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding?.currentweight?.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        binding?.btnCalculate?.setOnClickListener()
        {
            if(binding?.currentweight?.text.toString().toDouble()>0 && binding?.currentheight?.text.toString().toDouble()>0 && k==1) {
                var BMIans: Double = ((binding?.currentweight?.text.toString()
                    .toDouble()) * 10000) / ((binding?.currentheight?.text.toString()
                    .toDouble()) * (binding?.currentheight?.text.toString().toDouble()))
                val roundedValue = String.format("%.2f", BMIans).toDouble()
                val intent = Intent(this, BMIresult::class.java)
                intent.putExtra("BMI", roundedValue)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
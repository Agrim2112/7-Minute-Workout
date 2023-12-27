package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnStart?.setOnClickListener()
        {
            val intent = Intent(this,Exercise::class.java)
            startActivity(intent)
        }

        binding?.btnBMI?.setOnClickListener()
        {
            val intent = Intent(this,BMI::class.java)
            startActivity(intent)
        }

        binding?.ivHistory?.setOnClickListener()
        {
            val intent = Intent(this,History::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding=null
    }
}

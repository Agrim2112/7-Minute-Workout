package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Exercise : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding :ActivityExerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var ExerciseTimer:CountDownTimer?=null
    private var ExerciseProgress=0
    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1
    private var tts:TextToSpeech?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val historyDao=(application as WorkoutApp).db.historyDao()

        insertToDatabase(historyDao)
        exerciseList=Constants.DefaultExerciseList()

        tts= TextToSpeech(this,this)
        setRestView()


    }

    private fun setrestProgress()
    {
        binding?.pbrest?.progress=restProgress

        restTimer=object: CountDownTimer(1000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.pbrest?.progress=10-restProgress
                binding?.tvTimer?.text=(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                setExerciseView()
            }
        }.start()

    }

    private fun setExerciseProgress()
    {
        binding?.pbexercise?.progress=ExerciseProgress

        ExerciseTimer=object: CountDownTimer(1000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                ExerciseProgress++
                binding?.pbexercise?.progress=30-ExerciseProgress
                binding?.tvTimerEx?.text=(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList!!.size-1)
                {
                    setRestView()
                }
                else{
                    val intent = Intent(this@Exercise,Finish::class.java)
                    startActivity(intent)
                }
            }
        }.start()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        customDialogforBackButton()

    }

    private fun customDialogforBackButton()
    {
        val BackDialog=Dialog(this)
        BackDialog.setContentView(R.layout.dialog_box)
        val Yes=BackDialog.findViewById<Button>(R.id.btnYES)
        val No=BackDialog.findViewById<Button>(R.id.btnNO)
        BackDialog.setCanceledOnTouchOutside(false)

        Yes.setOnClickListener()
        {
            BackDialog.dismiss()
            finish()
        }
        No.setOnClickListener()
        {
            BackDialog.dismiss()
        }

        BackDialog.show()
    }

    private fun setRestView()
    {
        binding?.flProgressBar?.visibility=View.VISIBLE
        binding?.tvStart?.visibility=View.VISIBLE
        binding?.tvStart2?.visibility=View.GONE
        binding?.flExercise?.visibility=View.GONE
        binding?.ivExercise?.visibility=View.GONE
        binding?.cvUpNext?.visibility=View.VISIBLE
        binding?.tvUpnext?.visibility=View.VISIBLE
        binding?.tvProgress?.visibility=View.INVISIBLE
        binding?.pbExercise?.visibility=View.INVISIBLE

        Speakout("Rest now for 10 seconds")
        if(currentExercisePosition!=exerciseList!!.size-1) {
            binding?.tvNextExercise?.text = (exerciseList!![currentExercisePosition + 1].getname())
            binding?.upcomivNext?.setImageResource(exerciseList!![currentExercisePosition + 1].getimage())
        }

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        setrestProgress()
    }

    private fun setExerciseView()
    {
        binding?.flProgressBar?.visibility=View.GONE
        binding?.tvStart?.visibility=View.GONE
        binding?.tvStart2?.visibility=View.VISIBLE
        binding?.flExercise?.visibility=View.VISIBLE
        binding?.ivExercise?.visibility=View.VISIBLE
        binding?.cvUpNext?.visibility=View.INVISIBLE
        binding?.tvUpnext?.visibility=View.INVISIBLE
        binding?.tvProgress?.visibility=View.VISIBLE
        binding?.pbExercise?.visibility=View.VISIBLE

        if(ExerciseTimer!=null){
            ExerciseTimer?.cancel()
            ExerciseProgress=0
        }
        binding?.tvProgress?.text=((currentExercisePosition+1).toString()+"/"+exerciseList!!.size)
        binding?.pbExercise?.progress=currentExercisePosition+1
        binding?.tvStart2?.text=(exerciseList!![currentExercisePosition].getname())
        binding?.ivExercise?.setImageResource(exerciseList!![currentExercisePosition].getimage())
        Speakout("Back to the exercise!  Do "+(exerciseList!![currentExercisePosition].getname())+" for 30 seconds")
        setExerciseProgress()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        if(ExerciseTimer!=null){
            ExerciseTimer?.cancel()
            ExerciseProgress=0
        }

        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }

        binding=null
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS)
        {
            val result=tts?.setLanguage(Locale.UK)

            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("Error","Something went wrong")
            }
            else
                Log.e("Error","Initialisation Error")
        }
    }

    private fun Speakout(Text:String)
    {
        tts!!.speak(Text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun insertToDatabase(historyDao:HistoryDao)
    {
        val cal=Calendar.getInstance()
        val time=cal.time
        val sdf=SimpleDateFormat("d MMM yyyy HH:mm:ss",Locale.getDefault())

        val date=sdf.format(time)
        Log.e("Date",date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(""))
        }
    }


}
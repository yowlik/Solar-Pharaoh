package com.plarium.rai.white

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.plarium.rai.R

class Gamb : AppCompatActivity() ,GameInt {
    lateinit var  rootLayout: LinearLayout
    lateinit var startBtn: Button
    lateinit var mGameView:Games
    lateinit var score: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamb)
        startBtn=findViewById(R.id.start_btn)
        rootLayout=findViewById(R.id.rootL)
        score=findViewById(R.id.scoreView)
        mGameView=Games(this,this)
        startBtn.setOnClickListener{

            rootLayout.addView(mGameView)
            startBtn.visibility= View.GONE
            score.visibility= View.GONE

        }
    }

    override fun closeGame(mScore: Int) {
        score.text="Score : $mScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility= View.VISIBLE
        score.visibility= View.VISIBLE

    }

}
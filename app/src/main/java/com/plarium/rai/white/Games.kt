package com.plarium.rai.white

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.plarium.rai.R
import java.lang.Exception

class Games (var c: Context, var gameInt: GameInt): View(c)
{
    private var myPaint: Paint?=null
    private var speed=1
    private var time=0
    private var score=0
    private val meteor=ArrayList<HashMap<String,Any>>()
    private var position=0

    var viewWidth=0
    var viewHeight=0
    init{
        myPaint= Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewWidth=this.measuredWidth
        viewHeight=this.measuredHeight
        if(time%700<10+speed){
            val map=HashMap<String,Any>()
            map["lane"]=(0..2).random()
            map["startTime"]=time
            meteor.add(map)
        }
        time=time+10+speed
        val meteorWidth=viewWidth/5
        val meteorHeight=meteorWidth+10
        myPaint!!.style= Paint.Style.FILL
        val d=resources.getDrawable(R.drawable.charas,null)
        d.setBounds(
            position* viewWidth / 3 + viewWidth / 15,
            viewHeight-2-3*meteorHeight,
            position*viewWidth/3+viewWidth/15+meteorWidth,
            viewHeight-2
        )
        d.draw(canvas!!)
        myPaint!!.color= Color.GREEN
        var highScore=0
        for( i in meteor.indices){
            try{
                val meteorX=meteor[i]["lane"] as Int*viewWidth/3+viewWidth/15
                val meteorY=time-meteor[i]["startTime"] as Int
                val d2=resources.getDrawable(R.drawable.clever,null)

                d2.setBounds(
                    meteorX+25,meteorY-meteorHeight,meteorX+meteorWidth-25,meteorY
                )
                d2.draw(canvas!!)
                if(meteor[i]["lane"] as Int==position){
                    if(meteorY>viewHeight-4-2*meteorHeight &&
                        meteorY<viewHeight-2){
                        meteor.removeAt(i)
                        score++
                        speed=1+Math.abs(score/8)
                        if(score>highScore){
                            highScore=score
                        }

                    }
                }
                if(meteorY>viewHeight+meteorHeight){
                    gameInt.closeGame(score)
                    score=0
                    speed=0
                    meteor.clear()

                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
        myPaint!!.color= Color.WHITE
        myPaint!!.textSize= 60f
        myPaint!!.style= Paint.Style.FILL_AND_STROKE
        canvas.drawText("Score : $score",80f,120f,myPaint!!)
        canvas.drawText("Speed : $speed",380f,120f,myPaint!!)
        invalidate()


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN->{
                val x1=event.x
                if(x1<viewWidth/2){
                    if(position>0){
                        position--
                    }
                }
                if(x1>viewWidth/2){
                    if(position<2){
                        position++
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP->{}
        }
        return true

    }


}
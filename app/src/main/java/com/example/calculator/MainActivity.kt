package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvDisplay : TextView? = null
    private var dotInNumber : Boolean = false
    private var numberBefore : Boolean = false
    private var hasOperator : Boolean = false
    private var hasResult : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplay = findViewById(R.id.tvDisplay)
    }

    fun onDigit(view : View){
        if (hasResult){
            onClear(view)
        }
        tvDisplay?.append((view as Button).text)
        numberBefore = true
    }

    fun onDot(view: View){
        if(numberBefore && !dotInNumber){
            tvDisplay?.append(".")
            dotInNumber = true
            numberBefore = false
        }
    }

    fun onOperator(view: View){
        tvDisplay?.text?.let {
            if(numberBefore && !hasOperator){
                tvDisplay?.append((view as Button).text)
                hasOperator = true
                dotInNumber = false
                numberBefore = false
            }
        }
    }

    fun onEqual(view: View){
       tvDisplay?.text?.let {
            var value = tvDisplay?.text.toString()
            var result = 0.0
           if(value.contains("+")){
                var values = value.split("+")
                result = values[0].toDouble() + values[1].toDouble()
                tvDisplay?.text = removeZero(result.toString())
            }
           if(value.contains("-")){
               var values = value.split("-")
               result = values[0].toDouble() - values[1].toDouble()
               tvDisplay?.text = removeZero(result.toString())
           }
           if(value.contains("x")){
               var values = value.split("x")
               result = values[0].toDouble() * values[1].toDouble()
               tvDisplay?.text = removeZero(result.toString())
           }
           if(value.contains("/")){
               var values = value.split("/")
               if ((values[1].toDouble())!=0.0){
                   result = values[0].toDouble() / values[1].toDouble()
                   tvDisplay?.text = removeZero(result.toString())
               }else{
                   tvDisplay?.text = "Pendejo jajaja"
               }
           }
           hasResult = true
       }
    }

    fun removeZero(value : String) : String{
        var values = value.split(".")
        if (values[1].toDouble()==0.0){
            return values[0]
        }else{
            return  value
        }
    }

    fun onClear(view: View){
        tvDisplay?.text = ""
        dotInNumber = false
        numberBefore = false
        hasOperator = false
        hasResult = false
    }
}
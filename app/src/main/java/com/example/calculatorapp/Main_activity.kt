/**
 Section 7
  */
package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main_activity : AppCompatActivity() {
    // textview
    var txt:TextView?=null
    // varable which will check if we put last char as dot or numeric value
    var lastnumeric=false
    var lastdot=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        // connecting id's
        txt=findViewById<TextView>(R.id.text)
    }
   // this fn will add numeric value to the string
    fun onclick(view: View)
    {
        // convert view as button and adding value
        txt?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    // if user press clr then it will remove the whole string
    fun onclear(view :View)
    {
        txt?.text=""
    }
    // this will only allow to enter one decimal
    fun ondecimal(view :View)
    {
        if(lastnumeric && !lastdot)
        {
            txt?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    // this fn is for = sign / performing cal
    fun onEqual(view :View)
    {
        if(lastnumeric)
        {
            var value=txt?.text.toString()
            // check if string is - then
            var prefix=""
            try {
                if(value.startsWith("-"))
                {
                     prefix="-";
                    value=value.substring(1)
                }

                if(value.contains("-"))
                {
                    var splitval=value.split("-")
                    var one=splitval[0]
                    var two=splitval[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    txt?.text=removezeros((one.toDouble()-two.toDouble()).toString())
                }
                else if(value.contains("+"))
                {
                    var splitval=value.split("+")
                    var one=splitval[0]
                    var two=splitval[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    txt?.text=removezeros((one.toDouble()+two.toDouble()).toString())
                }
                else if(value.contains("*"))
                {
                    var splitval=value.split("*")
                    var one=splitval[0]
                    var two=splitval[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    txt?.text=removezeros((one.toDouble()*two.toDouble()).toString())
                }
                else if(value.contains("÷"))
                {
                    var splitval=value.split("÷")
                    var one=splitval[0]
                    var two=splitval[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    txt?.text=removezeros((one.toDouble()/two.toDouble()).toString())
                }

            }
            catch (e :ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
    // thi will remove all zeros from end after .
    fun removezeros(value :String):String
    {
        var res=value
        if(value.contains(".0"))
        {
            res=value.substring(0,value.length-2)
        }
        return res
    }
    // fn will allow to user to enter nly one operator
    fun onoperator(view :View)
    {
        txt?.text?.let {
            if(lastnumeric && !isoperatoraddes(it.toString()))
            {
                txt?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }
    fun isoperatoraddes(vale:String) :Boolean
    {
        return if(vale.startsWith("-"))
        {
            false;
        }
        else
        {
            vale.contains("/")
            || vale.contains("*")
            || vale.contains("+")
            || vale.contains("-")
        }
    }
}
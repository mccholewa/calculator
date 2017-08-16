package com.calculator.calculator.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import com.calculator.calculator.R
import butterknife.BindView
import butterknife.OnClick
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*
import com.calculator.calculator.Activity.Operations.Calculator
import kotlinx.android.synthetic.main.activity_main.view.*
import android.R.id.button1




class MainActivity : AppCompatActivity() {
    private val calc: Calculator = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.one)
    fun keypadClick(view:View){
        keypadClickCase(view.id)
    }

    //fun keypadClicked(id: Int){keypadClickCase(id)}

    @OnClick(R.id.four)


    fun keypadClickCase(id:Int)
    {
//        when(id){
//            one.id -> equation.text =  addChar(equation,'1')
//            two.id -> equation.text =  addChar(equation,'2')
//            three.id -> equation.text =  addChar(equation,'3')
//        }
    }


    fun addChar(textView:TextView,charToAdd:Char):String
    {
        var newText = textView.text.toString()
        newText += charToAdd
        return newText
    }
}







class Btn(setLabel:String,setAction: Action ){
    var label:String =setLabel
    var action:Action=setAction
}
enum class Action{
    VALUE,
    ADD,
    MIN,
    MUL,
    DIV,
    DEL,
    C,
    DOT,
    OPENBRACKET,
    CLOSEBRACKET,
    EQUAL
}
//var keypad  = mutableListOf<Btn>()
//keypad.add(Btn("7",Action.VALUE))
//keypad.add(Btn("8",Action.VALUE))
//keypad.add(Btn("9",Action.VALUE))
//keypad.add(Btn("DEL",Action.DEL))
//keypad.add(Btn("C",Action.C))
//keypad.add(Btn("4",Action.VALUE))
//keypad.add(Btn("5",Action.VALUE))
//keypad.add(Btn("6",Action.VALUE))
//keypad.add(Btn("+",Action.ADD))
//keypad.add(Btn("-",Action.MIN))
//keypad.add(Btn("1",Action.VALUE))
//keypad.add(Btn("2",Action.VALUE))
//keypad.add(Btn("3",Action.VALUE))
//keypad.add(Btn("X",Action.MUL))
//keypad.add(Btn("/",Action.DIV))
//keypad.add(Btn(".",Action.DOT))
//keypad.add(Btn("0",Action.VALUE))
//keypad.add(Btn("(",Action.OPENBRACKET))
//keypad.add(Btn(")",Action.CLOSEBRACKET))
//keypad.add(Btn("=",Action.EQUAL))



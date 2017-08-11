package com.calculator.calculator.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.calculator.calculator.R
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var keypad  = mutableListOf<Btn>()
        keypad.add(Btn("7",Action.VALUE))
        keypad.add(Btn("8",Action.VALUE))
        keypad.add(Btn("9",Action.VALUE))
        keypad.add(Btn("DEL",Action.DEL))
        keypad.add(Btn("C",Action.C))
        keypad.add(Btn("4",Action.VALUE))
        keypad.add(Btn("5",Action.VALUE))
        keypad.add(Btn("6",Action.VALUE))
        keypad.add(Btn("+",Action.ADD))
        keypad.add(Btn("-",Action.MIN))
        keypad.add(Btn("1",Action.VALUE))
        keypad.add(Btn("2",Action.VALUE))
        keypad.add(Btn("3",Action.VALUE))
        keypad.add(Btn("X",Action.MUL))
        keypad.add(Btn("/",Action.DIV))
        keypad.add(Btn(".",Action.DOT))
        keypad.add(Btn("0",Action.VALUE))
        keypad.add(Btn("(",Action.OPENBRACKET))
        keypad.add(Btn(")",Action.CLOSEBRACKET))
        keypad.add(Btn("=",Action.EQUAL))
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




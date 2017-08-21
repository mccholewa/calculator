package com.calculator.calculator.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import com.calculator.calculator.R
import android.view.WindowManager


class MainActivity : LifecycleActivity() {
    //private val viewModel = viewModel.BaseViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    }

//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        binding =  .inflate(inflater, container, false)
//        return binding.root
//    }

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



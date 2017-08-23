package com.calculator.calculator.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import com.calculator.calculator.R
import android.view.WindowManager
import fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_base.*
import viewModels.BaseViewModel


class MainActivity : LifecycleActivity() {
    private val viewModel = BaseViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        val baseFragment = BaseFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, baseFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
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



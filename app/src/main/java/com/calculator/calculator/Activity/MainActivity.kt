package com.calculator.calculator.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.calculator.calculator.R
import kotlinx.android.synthetic.main.activity_main.*
import android.text.SpannableStringBuilder
import android.view.View
import android.view.WindowManager
import android.view.LayoutInflater
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    //val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListener()
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =  .inflate(inflater, container, false)
        return binding.root
    }
    fun setOnClickListener(){
        zero.setOnClickListener { addChar('0') }
        one.setOnClickListener { addChar('1') }
        two.setOnClickListener { addChar('2') }
        three.setOnClickListener {addChar('3') }
        four.setOnClickListener {  addChar( '4') }
        five.setOnClickListener {  addChar('5') }
        six.setOnClickListener {addChar('6') }
        seven.setOnClickListener {addChar('7') }
        eight.setOnClickListener { addChar('8') }
        nine.setOnClickListener { addChar('9') }
        plus.setOnClickListener { addChar('+') }
        minus.setOnClickListener {addChar('-') }
        divide.setOnClickListener { addChar('÷') }
        multiply.setOnClickListener { addChar('x') }
        openbracket.setOnClickListener {  addChar('(') }
        closebracket.setOnClickListener { addChar(')') }
        dot.setOnClickListener {  addChar('.') }
        del.setOnClickListener { delete() }
        c.setOnClickListener { deleteAll() }
        equal.setOnClickListener { }
    }
    fun addChar(charToAdd:Char){
        var firstHalf = equation.text.toString().substring(0,equation.selectionEnd)
        var secondHalf =  equation.text.toString().substring(equation.selectionEnd)
        var newText = SpannableStringBuilder(firstHalf + charToAdd + secondHalf)
        var privCursorPosition = equation.selectionEnd
        equation.text =newText
        if(equation.text.isNotEmpty())
            equation.setSelection(privCursorPosition)
        else equation.setSelection(privCursorPosition)
    }
    fun delete() {
        if(equation.text.isNotEmpty() && equation.selectionEnd!=0) {
            var newText = equation.text.removeRange(equation.selectionEnd-1, equation.selectionEnd).toString()
            var privCursorPosition = equation.selectionEnd
            equation.text = SpannableStringBuilder(newText)
            equation.setSelection(privCursorPosition-1)
        }
    }
    fun deleteAll(){
        equation.text = SpannableStringBuilder("")
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



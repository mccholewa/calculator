package viewModels

import com.calculator.calculator.activity.Calculator
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.SpannableStringBuilder
import com.calculator.calculator.R
import com.calculator.calculator.activity.LiveDataDelegate
import com.calculator.calculator.activity.UiActionsLiveData
import fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * Created by mcholewa on 18/08/2017.
 */

class BaseViewModel : ViewModel() {
    private val data = Calculator()
    val liveData = LiveDataDelegate(data)
    private var state by liveData
    val uiActions = UiActionsLiveData()
    val selectionEnd =  uiActions.invoke {(it as BaseFragment).binding.equation.selectionEnd}

    fun load() {
    }


    fun onClickZero(){
        state = state.copy(mEquation = addChar('0'))
    }
    fun onClickOne(){
        state = state.copy(mEquation = addChar('1'))
    }
    fun onClickTwo(){
        state = state.copy(mEquation = addChar('2'))
    }
    fun onClickThree(){
        state = state.copy(mEquation = addChar('3'))
    }
    fun onClickFour(){
        state = state.copy(mEquation = addChar('4'))
    }
    fun onClickFive(){
        state = state.copy(mEquation = addChar('5'))
    }
    fun onClickSix(){
        state = state.copy(mEquation = addChar('6'))
    }
    fun onClickSeven(){
        state = state.copy(mEquation = addChar('7'))
    }
    fun onClickEight(){
        state = state.copy(mEquation = addChar('8'))
    }
    fun onClickNine(){
        state = state.copy(mEquation = addChar('9'))
    }
    fun onClickDel(){
        state = state.copy()
    }
    fun onClickC(){
        state = state.copy()
    }
    fun onClickEqual(){
        state = state.copy()
    }
    fun onClickPlus(){
        state = state.copy(mEquation = addChar('+'))
    }
    fun onClickMinus(){
        state = state.copy(mEquation = addChar('-'))
    }
    fun onClickDivide(){
        state = state.copy(mEquation = addChar('/'))
    }
    fun onClickMultiply(){
        state = state.copy(mEquation = addChar('x'))
    }
    fun onClickDot(){
        state = state.copy(mEquation = addChar('.'))
    }
    fun onClickOpenBracket(){
        state = state.copy(mEquation = addChar('('))
    }
    fun onClickCloseBracket(){
        state = state.copy(mEquation = addChar(')'))
    }


    fun addChar(charToAdd:Char):Editable{
        var newEquation =  SpannableStringBuilder(state.mEquation.toString() + Char)
        return newEquation
    }

    fun addChar(charToAdd:Char,equa: Editable ){
        var firstHalf = state.mEquation.toString().substring(0, selectionEnd)
        var secondHalf =  state.mEquation.toString().substring(selectionEnd)
        var newText = SpannableStringBuilder(firstHalf + charToAdd + secondHalf)
        var privCursorPosition = equation.selectionEnd
        state =state.copy(mEquation = newText)
        if(state.mEquation.isNotEmpty())
            equation.setSelection(privCursorPosition)
        else equation.setSelection(privCursorPosition-1)
    }



    override fun onCleared() {

    }



    fun eval(str: String): Double {
        return object : Any() {
            internal var pos = -1
            internal var ch: Int = 0

            internal fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            internal fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            internal fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            internal fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt()))
                        x += parseTerm() // addition
                    else if (eat('-'.toInt()))
                        x -= parseTerm() // subtraction
                    else
                        return x
                }
            }

            internal fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt()))
                        x *= parseFactor() // multiplication
                    else if (eat('/'.toInt()))
                        x /= parseFactor() // division
                    else
                        return x
                }
            }

            internal fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus

                var x: Double
                val startPos = this.pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = java.lang.Double.parseDouble(str.substring(startPos, this.pos))
//                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) { // functions
//                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
//                    val func = str.substring(startPos, this.pos)
//                    x = parseFactor()
//                    if (func == "sqrt")
//                        x = Math.sqrt(x)
//                    else if (func == "sin")
//                        x = Math.sin(Math.toRadians(x))
//                    else if (func == "cos")
//                        x = Math.cos(Math.toRadians(x))
//                    else if (func == "tan")
//                        x = Math.tan(Math.toRadians(x))
//                    else
//                        throw RuntimeException("Unknown function: " + func)
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
        }.parse()
    }
}

//    fun getEquation(): Editable{
//        return data.mEquation
//    }
//    fun  getHistory():String{
//        return data.mHistory
//    }
//    fun delete() {
//        if(equation.text.isNotEmpty() && equation.selectionEnd!=0) {
//            var newText = equation.text.removeRange(equation.selectionEnd-1, equation.selectionEnd).toString()
//            var privCursorPosition = equation.selectionEnd
//            equation.text = SpannableStringBuilder(newText)
//            equation.setSelection(privCursorPosition-1)
//        }
//    }
//    fun deleteAll(){
//        equation.text = SpannableStringBuilder("")
//    }
//        fun setOnClickListener(){
//        zero.setOnClickListener { addChar('0') }
//        one.setOnClickListener { addChar('1') }
//        two.setOnClickListener { addChar('2') }
//        three.setOnClickListener {addChar('3') }
//        four.setOnClickListener {  addChar( '4') }
//        five.setOnClickListener {  addChar('5') }
//        six.setOnClickListener {addChar('6') }
//        seven.setOnClickListener {addChar('7') }
//        eight.setOnClickListener { addChar('8') }
//        nine.setOnClickListener { addChar('9') }
//        plus.setOnClickListener { addChar('+') }
//        minus.setOnClickListener {addChar('-') }
//        divide.setOnClickListener { addChar('÷') }
//        multiply.setOnClickListener { addChar('x') }
//        openbracket.setOnClickListener {  addChar('(') }
//        closebracket.setOnClickListener { addChar(')') }
//        dot.setOnClickListener {  addChar('.') }
//        del.setOnClickListener { delete() }
//        c.setOnClickListener { deleteAll() }
//        equal.setOnClickListener { }
//    }

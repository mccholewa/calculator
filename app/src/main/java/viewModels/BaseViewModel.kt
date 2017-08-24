package viewModels

import com.calculator.calculator.activity.Calculator
import android.arch.lifecycle.ViewModel
import android.text.SpannableStringBuilder
import android.widget.EditText
import com.calculator.calculator.activity.LiveDataDelegate
import com.calculator.calculator.activity.UiActionsLiveData
import fragments.BaseFragment

/**
 * Created by mcholewa on 18/08/2017.
 */

class BaseViewModel : ViewModel() {
    private val data = Calculator()
    val liveData = LiveDataDelegate(data)
    private var state by liveData
    val uiActions = UiActionsLiveData()
    var selectionEnd = 0

    fun load() {
    }
    fun init(){
        uiActions.invoke { selectionEnd = (it as BaseFragment).binding.equation.selectionEnd }
    }
    fun setSelection(position:Int)
    {
        uiActions.invoke {(it as BaseFragment).binding.equation.setSelection(position) }
    }

    override fun onCleared() {
    }

    fun onClickZero(){
        addChar('0')
    }
    fun onClickOne(){
        addChar('1')
    }
    fun onClickTwo(){
        addChar('2')
    }
    fun onClickThree(){
        addChar('3')
    }
    fun onClickFour(){
        addChar('4')
    }
    fun onClickFive(){
        addChar('5')
    }
    fun onClickSix(){
        addChar('6')
    }
    fun onClickSeven(){
        addChar('7')
    }
    fun onClickEight(){
        addChar('8')
    }
    fun onClickNine(){
        addChar('9')
    }
    fun onClickDel(){
        del()
    }
    fun onClickC(){
         state = state.copy(mEquation = SpannableStringBuilder(""))
    }
    fun onClickEqual(){
        equal()
    }
    fun onClickPlus(){
        addOperation('+')
    }
    fun onClickMinus(){
        addOperation('-')
    }
    fun onClickDivide(){
        addOperation('/')
    }
    fun onClickMultiply(){
        addOperation('*')
    }
    fun onClickDot(){
        addOperation('.')
    }
    fun onClickOpenBracket(){
        addChar('(')
    }
    fun onClickCloseBracket(){
        addChar(')')
    }

    fun equal(){
        var newText =((eval(state.mEquation.toString()))).toString()
        if(newText.substring(newText.length-2,newText.length) == ".0")
            newText = newText.substring(0,newText.length-2)
        state=state.copy(mHistory = state.mEquation.toString())
        state=state.copy(mEquation = SpannableStringBuilder(newText))
    }

    fun addChar(charToAdd:Char){
            state = state.copy(mEquation =  state.mEquation.append(charToAdd))
    }

    fun addOperation(operationToAdd:Char) {
        if (state.mEquation.isNotEmpty()) {
            var lastChar: Char = state.mEquation.substring(state.mEquation.length - 1, state.mEquation.length)[0]
            if (lastChar != operationToAdd) {
                state = state.copy(mEquation = state.mEquation.append(operationToAdd))
            }
        }
        if (state.mEquation.isEmpty() && operationToAdd != '-')
            state = state.copy(mEquation = state.mEquation.append(operationToAdd))
    }

    fun del() {
        if(state.mEquation.isNotEmpty())
            state = state.copy(mEquation = state.mEquation.delete(state.mEquation.length-1,state.mEquation.length))
    }

        fun eval(str: String): Double {
            return object : Any() {
                internal var pos = -1
                internal var ch: Int = 0

                internal fun nextChar() {
                    ch = if (++pos < str.length) str[pos].toInt() else -1
                }

                internal fun eat(charToEat: Int): Boolean {
                    while ( ch == ' '.toInt()) nextChar()
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
                    } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) { // functions
                        while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                        val func = str.substring(startPos, this.pos)
                        x = parseFactor()
                        if (func == "sqrt")
                            x = Math.sqrt(x)
                        else if (func == "sin")
                            x = Math.sin(Math.toRadians(x))
                        else if (func == "cos")
                            x = Math.cos(Math.toRadians(x))
                        else if (func == "tan")
                            x = Math.tan(Math.toRadians(x))
                        else
                            throw RuntimeException("Unknown function: " + func)
                    } else {
                        throw RuntimeException("Unexpected: " + ch.toChar())
                    }
                    if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                    return x
                }
            }.parse()
        }
}

//    fun addChar(charToAdd:Char, equation: EditText){
//        var firstHalf = equation.text.toString().substring(0, equation.selectionEnd)
//        var secondHalf =  equation.text.toString().substring(equation.selectionEnd)
//        var newText = SpannableStringBuilder(firstHalf + charToAdd + secondHalf)
//        var privCursorPosition = equation.selectionEnd
//        equation.text = SpannableStringBuilder(newText)
//        if(equation.text.isNotEmpty()) {
//            equation.setSelection(privCursorPosition+1)
//        }
//        else
//            equation.setSelection(privCursorPosition)
//    }

//    fun del() {
//        if(equation.text.isNotEmpty() && equation.selectionEnd!=0) {
//            var newText = equation.text.removeRange(equation.selectionEnd-1, equation.selectionEnd).toString()
//            var privCursorPosition = equation.selectionEnd
//            equation.text = SpannableStringBuilder(newText)
//            equation.setSelection(privCursorPosition-1)
//        }
//    }

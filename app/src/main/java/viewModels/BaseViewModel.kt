package viewModels

import android.arch.lifecycle.ViewModel
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.EditText
import com.calculator.calculator.activity.Calculator
import com.calculator.calculator.activity.LiveDataDelegate
import com.calculator.calculator.activity.UiActionsLiveData
import java.text.DecimalFormat

/**
 * Created by mcholewa on 18/08/2017.
 */

class BaseViewModel : ViewModel() {
    private val data = Calculator()
    val liveData = LiveDataDelegate(data)
    private var state by liveData
    val uiActions = UiActionsLiveData()

    fun load() {
    }


    override fun onCleared() {
    }

    fun onClickZero() {
        addChar('0')
    }

    fun onClickOne() {
        addChar('1')
    }

    fun onClickTwo() {
        addChar('2')
    }

    fun onClickThree() {
        addChar('3')
    }

    fun onClickFour() {
        addChar('4')
    }

    fun onClickFive() {
        addChar('5')
    }

    fun onClickSix() {
        addChar('6')
    }

    fun onClickSeven() {
        addChar('7')
    }

    fun onClickEight() {
        addChar('8')
    }

    fun onClickNine() {
        addChar('9')
    }

    fun onClickDel() {
        del()
    }

    fun onClickC() {
        state = state.copy(mEquation = "")
    }

    fun onClickEqual() {
        equal()
    }

    fun onClickMinus() {
        if (checkAddMinus()) {
            replaceChar('-')
        } else
            addChar('-')
    }

    fun onClickPlus() {
        if (state.mEquation.isNotEmpty()) {
            if (isLastCharOperation())
                replaceChar('+')
            else addChar('+')
        }
    }

    fun onClickDivide() {
        if (state.mEquation.isNotEmpty()) {
            if (isLastCharOperation())
                replaceChar('/')
            else addChar('/')
        }
    }

    fun onClickMultiply() {
        if (state.mEquation.isNotEmpty()) {
            if (isLastCharOperation())
                replaceChar('x')
            else addChar('x')
        }
    }

    fun onClickDot() {
        if (state.mEquation.isNotEmpty()) {
            if (isLastCharOperation())
                replaceChar('.')
            else addChar('.')
        }
    }

    fun onClickOpenBracket() {
        addChar('(')
    }

    fun onClickCloseBracket() {
        addChar(')')
    }

    fun equal() {
        var newText = state.mEquation
        val formatter = DecimalFormat("#.####")
        if (isOperation(newText[newText.length-1]) && isOperation(newText[state.mEquation.length-2]))
            newText = eval(newText.substring(0,newText.length-2)).toString()
        if (isOperation(newText[newText.length-1]))
            newText = eval(newText.substring(0,newText.length-1)).toString()
        var result = eval(newText)
        if (result == null) {
            newText = ""
        } else
            newText = formatter.format(result).toString()



        state = state.copy(mHistory = state.mEquation)
        state = state.copy(mEquation = newText)
    }


    fun addChar(charToAdd: Char) {


        state = state.copy(mEquation = state.mEquation +charToAdd)
    }

    fun replaceChar(charToAdd: Char) {
        if (state.mEquation.isNotEmpty()) {
            if (state.mEquation.length >= 2)
                if (isOperation(state.mEquation[state.mEquation.length - 2]))
                    state = state.copy(mEquation = state.mEquation.substring(0, state.mEquation.length - 2) + charToAdd)
                else
                    state = state.copy(mEquation = state.mEquation.substring(0, state.mEquation.length - 1) + charToAdd)
        }
    }

    fun checkAddMinus(): Boolean {
        val operations = arrayListOf<Char>('-', '+')
        if (state.mEquation.isNotEmpty())
            if (operations.contains(state.mEquation[state.mEquation.length - 1]))
                return true
        return false
    }

    fun isOperation(char: Char): Boolean {
        val operations = arrayListOf<Char>('-', '+', 'x', '/')
        if (operations.contains(char))
            return true
        return false
    }

    fun isLastCharOperation(): Boolean {
        if (state.mEquation.isNotEmpty())
            if (isOperation(state.mEquation[state.mEquation.length - 1]))
                return true
        return false
    }

    fun del() {
        if (state.mEquation.isNotEmpty())
            state = state.copy(mEquation = state.mEquation.removeRange(state.mEquation.length - 1, state.mEquation.length))
    }

    fun eval(str: String): Double? {
        try {
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
                        if (eat('x'.toInt()))
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
                    } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch === '.'.toInt()) { // numbers
                        while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch === '.'.toInt()) nextChar()
                        x = java.lang.Double.parseDouble(str.substring(startPos, this.pos))
                    } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) {
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
        } catch (e: Exception) {
            return null
        }
    }
}


//                              Cursor logic

//    fun del() {
//        if(equation.text.isNotEmpty() && equation.selectionEnd!=0) {
//            var newText = equation.text.removeRange(equation.selectionEnd-1, equation.selectionEnd).toString()
//            var privCursorPosition = equation.selectionEnd
//            equation.text = SpannableStringBuilder(newText)
//            equation.setSelection(privCursorPosition-1)
//        }
//    }

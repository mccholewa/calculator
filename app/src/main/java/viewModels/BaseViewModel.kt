package viewModels

import com.calculator.calculator.activity.Calculator
import android.arch.lifecycle.ViewModel
import android.text.SpannableStringBuilder
import com.calculator.calculator.activity.LiveDataDelegate

/**
 * Created by mcholewa on 18/08/2017.
 */

class BaseViewModel : ViewModel(){
    private val data = Calculator()
    val liveData = LiveDataDelegate(data)
    private var state by liveData
    //val uiActions =  UiActionsLiveData()
    fun load(){
        data.mEquation = SpannableStringBuilder("3462346")
        data.mHistory = "3462346"
        state.copy(data)
    }










//    fun getEquation(): Editable{
//        return data.mEquation
//    }
//    fun  getHistory():String{
//        return data.mHistory
//    }
//    fun addChar(charToAdd:Char){
//        state = state.copy(mEquation)
//        var firstHalf = calculator.mEquation.toString().substring(0, equation.selectionEnd)
//        var secondHalf =  calculator.mEquation.toString().substring(R.id.equation.selectionEnd)
//        var newText = SpannableStringBuilder(firstHalf + charToAdd + secondHalf)
//        var privCursorPosition = R.id.equation.selectionEnd
//        calculator.mEquation =newText
//        if(calculator.mEquation.isNotEmpty())
//            R.id.equation.setSelection(privCursorPosition)
//        else R.id.equation.setSelection(privCursorPosition-1)
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
}
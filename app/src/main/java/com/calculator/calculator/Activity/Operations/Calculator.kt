package com.calculator.calculator.Activity.Operations
import com.calculator.calculator.R
import kotlinx.android.synthetic.main.activity_main.*
/**
 * Created by mcholewa on 16/08/2017.
 */


class Calculator{
    var mHistory: String = ""
    var mEquation: String = ""
    var mSelectonMoved = false

    fun reset(){
        mSelectonMoved = false
    }
}
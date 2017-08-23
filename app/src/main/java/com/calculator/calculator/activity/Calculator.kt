package com.calculator.calculator.activity

import android.text.Editable
import android.text.SpannableStringBuilder

/**
 * Created by mcholewa on 16/08/2017.
 */

data class Calculator(
        val mHistory: String = "",
        val mEquation: Editable = SpannableStringBuilder(""),
        val mSelectionEnd: Int = 0,
        val mSelectonMoved: Boolean= false
)
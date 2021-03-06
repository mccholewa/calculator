package com.calculator.calculator.activity

import android.text.Editable
import android.text.SpannableStringBuilder

/**
 * Created by mcholewa on 16/08/2017.
 */

data class Calculator(
        var mHistory: String = "",
        var mEquation: Editable = SpannableStringBuilder(""),
        var mSelectonMoved: Boolean= false
)
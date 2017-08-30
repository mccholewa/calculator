package com.calculator.calculator.activity

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.EditText

/**
 * Created by mcholewa on 29/08/2017.
 */

@BindingAdapter("cursorPosition")
fun cursorPosition(view: EditText, text: String) {
    Log.e("cursorPositionBefore", view.selectionEnd.toString())
    view.setSelection(text.length)
    Log.e("cursorPositionAfter", view.selectionEnd.toString())
}
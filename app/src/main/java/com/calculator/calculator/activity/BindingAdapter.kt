package com.calculator.calculator.activity

import android.databinding.BindingAdapter
import android.widget.EditText

/**
 * Created by mcholewa on 29/08/2017.
 *  EditText BindingAdapter that moves cursor to right side
 */

@BindingAdapter("cursorPosition")
fun cursorPosition(view: EditText, text: String) {
    view.setSelection(text.length)
}
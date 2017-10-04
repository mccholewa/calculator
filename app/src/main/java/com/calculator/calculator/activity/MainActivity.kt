package com.calculator.calculator.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import com.calculator.calculator.R
import android.view.WindowManager
import fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_base.*


class MainActivity : LifecycleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        val baseFragment = BaseFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, baseFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    override fun onStart() {
        super.onStart()
        equation.customSelectionActionModeCallback = SelectionEditText()
    }
}


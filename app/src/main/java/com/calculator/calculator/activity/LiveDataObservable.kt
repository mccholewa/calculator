package com.calculator.calculator.activity

/**
 * Created by mcholewa on 21/08/2017.
 */
import android.arch.lifecycle.LifecycleOwner

interface LiveDataObservable<out T> {

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit)

    fun observeForever(observer: (T) -> Unit)
}
package com.calculator.calculator.activity

import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem


/**
 * Created by mcholewa on 30/08/2017.
 */
 class SelectionEditText : ActionMode.Callback{
    override
    fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        return true
    }
    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        menu.removeItem(android.R.id.shareText)
        return true
    }
    override
    fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {
    }
}
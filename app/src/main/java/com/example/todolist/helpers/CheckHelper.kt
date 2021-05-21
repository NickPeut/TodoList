package com.example.todolist.helpers

import com.example.todolist.R

fun showCheck(isDone: Boolean) :Int {
    if (isDone) {
        return R.drawable.ic_baseline_check_circle_outline_24
    }
    return R.drawable.ic_baseline_radio_button_unchecked_24
}
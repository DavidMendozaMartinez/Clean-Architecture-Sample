package com.davidmendozamartinez.clean.architecture.sample.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Double.toPrettifiedString(): String {
    val df = DecimalFormat("#.####")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}

fun Date.toPrettifiedString(): String =
    SimpleDateFormat.getDateTimeInstance().run { format(this@toPrettifiedString) }
package com.example.clean_architecture.core_lib.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

val numberFormat = DecimalFormat("#,##0", DecimalFormatSymbols.getInstance().apply {
    groupingSeparator = '.'
    decimalSeparator = ','
})

fun Int.format(): String {
    return numberFormat.format(this)
}

fun Long.format(): String {
    return numberFormat.format(this)
}

fun Double.format(): String {
    return numberFormat.format(this)
}

fun Float.format(): String {
    return numberFormat.format(this)
}
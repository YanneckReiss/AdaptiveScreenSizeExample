package com.yanneckreiss.adaptivescreensizeexample.core

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val DATE_TIME_PATTERN = "dd. MMMM yyyy, HH:mm:ss"

fun ZonedDateTime.toLocalizedFormat(): String {

    val formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)
        .withLocale(Locale.getDefault())

    return this.format(formatter)
}

package com.spudpickles.itunessearch.data.entities

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class ITunesItem(
    val artistName: String,
    val trackName: String,
    val trackPrice: Number,
    val releaseDateString: String,
    val primaryGenreName: String
) {
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    val formattedPrice get() = if (0 >= trackPrice.toFloat()) "" else "$$trackPrice"
    val formattedDate: String
        get() = try {
            val date = format.parse(releaseDateString)

            if (date != null)
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)
            else ""
        } catch (e: Exception) {
            ""
        }
}

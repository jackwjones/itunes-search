package com.spudpickles.itunessearch

import com.spudpickles.itunessearch.data.entities.ITunesItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Unit tests for the iTunes item data class helpers.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ItemUnitTest {
    @Test
    fun testGoodValues() {
        val i = ITunesItem(
            artistName = "artist",
            trackName = "track",
            trackPrice = 1.99,
            releaseDateString = "2000-01-01T00:00:00Z",
            primaryGenreName = "genre")
        assertNotEquals("", i.formattedPrice)
        assertNotEquals("", i.formattedDate)
    }

    @Test
    fun testNegativePrice() {
        val i = ITunesItem(
            artistName = "artist",
            trackName = "track",
            trackPrice = -1.99,
            releaseDateString = "2000-01-01T00:00:00Z",
            primaryGenreName = "genre")
        assertEquals("", i.formattedPrice)
    }

    @Test
    fun testBlankDate() {
        val i = ITunesItem(
            artistName = "artist",
            trackName = "track",
            trackPrice = 1.99,
            releaseDateString = "",
            primaryGenreName = "genre")
        assertEquals("", i.formattedDate)
    }

    @Test
    fun testBadDate() {
        val i = ITunesItem(
            artistName = "artist",
            trackName = "track",
            trackPrice = 1.99,
            releaseDateString = "Jan 1, 2000",
            primaryGenreName = "genre")
        assertEquals("", i.formattedDate)
    }
}
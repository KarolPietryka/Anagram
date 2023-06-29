package org.zooplus.anagrams.service.anagram

object AnagramChecker {
    const val ACSII_BOTTOM_CHAR = 'a'
    fun check(input: String, word: String): Boolean {
        if (input.length != word.length) return false

        val charCounts = IntArray(256)
        for (i in input.indices) {
            charCounts[input[i].lowercaseChar() - ACSII_BOTTOM_CHAR]++
            charCounts[word[i].lowercaseChar() - ACSII_BOTTOM_CHAR]--
        }

        for (count in charCounts) {
            if (count != 0) return false
        }

        return true
    }
}
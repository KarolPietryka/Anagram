package org.zooplus.anagrams.service.anagram

object AnagramChecker {
    fun check(input: String, word: String): Boolean {
        val normalizedInput = input.replace(" ", "").lowercase()
        val normalizedWord = word.replace(" ", "").lowercase()

        if (normalizedInput.length != normalizedWord.length) return false
        val charCounts = IntArray(26)
        for (i in normalizedInput.indices) {
            charCounts[normalizedInput[i] - 'a']++
            charCounts[normalizedWord[i] - 'a']--
        }

        for (count in charCounts) {
            if (count != 0) return false
        }

        return true
    }
}
package org.zooplus.anagrams.service.anagram.searcher.impl

import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.service.anagram.searcher.AnagramSearcher

class UnsortedDictionaryAnagramSearcher constructor(
    private val directoryReader: DictionaryReader
): AnagramSearcher {
    override fun getAnagram(input: String): String? {
        val directoryContent = directoryReader.getFromDirectory()
        var longestAnagram: String? = null

        directoryContent.dictionaryContent.forEach {
            if (goo(input, it) && (longestAnagram == null || it.length > (longestAnagram?.length ?: 0))) {
                longestAnagram = it
            }
        }
        return longestAnagram
    }
    fun goo(input: String, word: String): Boolean {
        if (input.length != word.length) return false

        val charCounts = IntArray(256)
        for (i in input.indices) {
            charCounts[input[i].lowercaseChar() - 'a']++
            charCounts[word[i].lowercaseChar() - 'a']--
        }

        for (count in charCounts) {
            if (count != 0) return false
        }

        return true
    }


}
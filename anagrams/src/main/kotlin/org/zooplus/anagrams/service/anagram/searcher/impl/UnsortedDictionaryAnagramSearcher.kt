package org.zooplus.anagrams.service.anagram.searcher.impl

import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.service.anagram.AnagramChecker
import org.zooplus.anagrams.service.anagram.searcher.AnagramSearcher

class UnsortedDictionaryAnagramSearcher constructor(
    private val directoryReader: DictionaryReader
): AnagramSearcher {
    override fun getAnagram(input: String): String? {
        val directoryContent = directoryReader.getFromDirectory()
        var longestAnagram: String? = null

        directoryContent.dictionaryContent.forEach {
            if (AnagramChecker.check(input, it) && (longestAnagram == null || it.length > (longestAnagram?.length ?: 0))) {
                longestAnagram = it
            }
        }
        return longestAnagram
    }


}
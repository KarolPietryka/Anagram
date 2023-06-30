package org.zooplus.anagrams.service.anagram.searcher.impl

import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import org.zooplus.anagrams.service.anagram.AnagramChecker
import org.zooplus.anagrams.service.anagram.searcher.AbstractAnagramSearcher

class UnsortedDictionaryAnagramSearcher constructor(
    directoryReader: DictionaryReader
): AbstractAnagramSearcher(directoryReader) {
    override fun getAnagram(input: String, directoryContent: DirectoryContent): String? {
        var longestAnagram: String? = null
        directoryContent.dictionaryContent.forEach {
            if (it != input && AnagramChecker.check(input, it) && (longestAnagram == null || it.length > (longestAnagram?.length ?: 0))) {
                longestAnagram = it
            }
        }
        return longestAnagram
    }

}
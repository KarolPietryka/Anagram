package org.zooplus.anagrams.service.anagram.searcher.impl

import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import org.zooplus.anagrams.service.anagram.AnagramChecker
import org.zooplus.anagrams.service.anagram.searcher.AbstractAnagramSearcher
import org.zooplus.anagrams.service.anagram.searcher.AnagramSearcher

class SortedDictionaryAnagramSearcher(
    directoryReader: DictionaryReader
): AbstractAnagramSearcher(directoryReader) {
    override fun getAnagram(input: String, directoryContent: DirectoryContent): String? {
        directoryContent.dictionaryContent.forEach {
            if (AnagramChecker.check(input, it)) {
                return it
            }
        }
        return null
    }

}
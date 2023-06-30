package org.zooplus.anagrams.service.anagram.searcher

import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent

abstract class AbstractAnagramSearcher (
    protected val directoryReader: DictionaryReader
): AnagramSearcher{
    override fun getAnagram(input: String): String? {
        val directoryContent = directoryReader.getFromDirectory()
        return getAnagram(input, directoryContent)
    }
    abstract fun getAnagram(input: String, directoryContent: DirectoryContent): String?
}
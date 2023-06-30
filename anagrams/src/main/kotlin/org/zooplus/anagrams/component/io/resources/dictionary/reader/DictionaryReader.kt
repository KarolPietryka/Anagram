package org.zooplus.anagrams.component.io.resources.dictionary.reader

import org.zooplus.anagrams.model.io.dictionary.DirectoryContent

interface DictionaryReader {
    fun getFromDirectory(): DirectoryContent
}
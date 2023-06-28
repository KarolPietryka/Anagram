package org.zooplus.anagrams.component.io.resources.dictionary

import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import org.zooplus.anagrams.model.io.dictionary.trance.DictionaryTranceEntity

interface DictionaryReader {
    fun getFromDirectory(): DirectoryContent
}
package org.zooplus.anagrams.model.io.dictionary

import org.zooplus.anagrams.model.io.dictionary.trance.DictionaryTranceEntity

data class DirectoryContent (
    val dictionaryEntities: List<DictionaryTranceEntity>
)
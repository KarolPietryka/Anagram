package org.zooplus.anagrams.component.io.resources.dictionary.writter

import java.nio.file.Path

interface DictionaryWriter {
    fun write(dictionaryContent: List<String>, newDictionaryPath: String): Path
}
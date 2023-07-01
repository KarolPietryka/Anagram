package org.zooplus.anagrams.component.io.resources.dictionary.writter

interface DictionaryWriter {
    fun write(words: List<String>, newDictionaryPath: String)
}
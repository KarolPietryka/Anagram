package org.zooplus.anagrams.service.anagram.impl

import org.springframework.stereotype.Service
import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.service.anagram.AnagramCalculator

@Service
class AnagramCalculatorImpl constructor(
    private val directoryReader: DictionaryReader
): AnagramCalculator {
    override fun getAnagrams(input: String): List<String>{
        directoryReader.getFromDirectory()
        return emptyList()
    }
}
package org.zooplus.anagrams.service.anagram.searcher.impl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent

@ExtendWith(MockitoExtension::class)
internal class SortedDictionaryAnagramSearcherTest {

    @Mock
    private lateinit var directoryReader: DictionaryReader

    @InjectMocks
    private lateinit var searcher: SortedDictionaryAnagramSearcher

    @Test
    fun `should return the longest anagram from the sorted dictionary`() {
        val words = listOf("hello", "world", "dworl", "l o r d w ")
        val sortedWords = words.sortedWith(compareByDescending { it.length })
        val directoryContent = DirectoryContent(sortedWords)
        whenever(directoryReader.getFromDirectory()).thenReturn(directoryContent)
        val anagram = searcher.getAnagram("world")
        assertEquals("l o r d w ", anagram)
    }
    @Test
    fun `should return the longest anagram from the sorted dictionary for 'astronomer'`() {
        val words = listOf("hello", "world", "moon starer", "astronome")
        val sortedWords = words.sortedWith(compareByDescending { it.length })
        val directoryContent = DirectoryContent(sortedWords)
        whenever(directoryReader.getFromDirectory()).thenReturn(directoryContent)
        val anagram = searcher.getAnagram("astronomer")
        assertEquals("moon starer", anagram)
    }
}

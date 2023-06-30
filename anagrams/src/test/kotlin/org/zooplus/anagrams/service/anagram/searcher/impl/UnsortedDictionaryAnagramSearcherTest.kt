package org.zooplus.anagrams.service.anagram.searcher.impl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent

@ExtendWith(MockitoExtension::class)
internal class UnsortedDictionaryAnagramSearcherTest {

    @Mock
    private lateinit var directoryReader: DictionaryReader

    @InjectMocks
    private lateinit var searcher: UnsortedDictionaryAnagramSearcher

    @Test
    fun `should return the longest anagram from the dictionary`() {
        val words = listOf("hello", "lordw")
        val directoryContent = DirectoryContent(words)

        // mock the dictionary reader
        whenever(directoryReader.getFromDirectory()).thenReturn(directoryContent)

        // 'world' has two anagrams in the list, 'dworl' and 'lordw'. 'lordw' is the longest.
        val anagram = searcher.getAnagram("world")

        assertEquals("lordw", anagram)
    }

    @Test
    fun `should return the first anagram from the dictionary`() {
        val words = listOf("hello", "world", "dworl", "lordw")
        val directoryContent = DirectoryContent(words)

        // mock the dictionary reader
        whenever(directoryReader.getFromDirectory()).thenReturn(directoryContent)

        // 'world' has two anagrams in the list, 'dworl' and 'lordw'. 'dworl' is the first.
        val anagram = searcher.getAnagram("world")

        assertEquals("dworl", anagram)
    }
}

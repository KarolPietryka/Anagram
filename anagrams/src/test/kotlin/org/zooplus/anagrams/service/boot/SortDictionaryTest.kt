package org.zooplus.anagrams.service.boot

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.AfterEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.zooplus.anagrams.component.boot.SortDictionary
import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.component.io.resources.dictionary.writter.DictionaryWriter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.service.io.resources.ResourcesService
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path


internal class SortDictionaryTest {

    private lateinit var fs: FileSystem
    private lateinit var dirPath: Path

    @BeforeEach
    fun setup() {
        fs = Jimfs.newFileSystem(Configuration.unix())
        dirPath = fs.getPath("/test")

        Files.createDirectory(dirPath)
    }
    @AfterEach
    fun teardown() {
        fs.close()
    }

    @Mock
    private lateinit var dictionaryReader: DictionaryReader
    @Mock
    private lateinit var dictionaryWriter: DictionaryWriter
    @Mock
    private lateinit var dictionaryProperties: DictionaryProperties
    @Mock
    private lateinit var resourcesService: ResourcesService
    //@Test
    fun `should return all words from all files in the directory`() {
        val dirPath = fs.getPath("/test")
        val file1 = dirPath.resolve("file1.txt")
        val file2 = dirPath.resolve("file2.txt")

        Files.write(file1, listOf("hello", "world"))
        Files.write(file2, listOf("test", "input"))

        whenever(dictionaryProperties.dictionaryDirPath).thenReturn(dirPath.toString())
        whenever(dictionaryProperties.sortedDictionaryPath).thenReturn("sorted.txt")


        SortDictionary(dictionaryReader, dictionaryWriter, dictionaryProperties, resourcesService).run(null)


        val expectedSortedContent = listOf("hello", "input", "test", "world")
        val sortedContent = Files.readAllLines(dirPath.resolve("sorted.txt"))

        assertEquals(expectedSortedContent, sortedContent)
        verify(dictionaryReader).getFromDirectory()
        verify(dictionaryWriter).write(expectedSortedContent,"sorted.txt" )

    }

}
package org.zooplus.anagrams.component.io.resources.dictionary.impl

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.zooplus.anagrams.component.io.resources.dictionary.writter.impl.DictionaryOverwritter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

internal class DictionaryOverwritterTest {

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
    @Test
    fun write() {
        val dirPath = fs.getPath("/test")

        val file1 = dirPath.resolve("file1.txt")
        val file2 = dirPath.resolve("file2.txt")

        Files.write(file1, listOf("hello", "world"))
        Files.write(file2, listOf("test", "input"))

        val dictionaryProperties: DictionaryProperties = mock()
        whenever(dictionaryProperties.dictionaryDirPath).thenReturn(dirPath.toString())

        DictionaryOverwritter(fs, dictionaryProperties).write(
            listOf("hello", "input", "test", "world"),
            "sorted.txt"
        )

        val expectedSortedContent = listOf("hello", "input", "test", "world")
        val sortedContent = Files.readAllLines(dirPath.resolve("/test/sorted.txt"))

        assertEquals(expectedSortedContent, sortedContent)
        assertEquals(1, countFilesInDirectory(dirPath))
    }

    private fun countFilesInDirectory(directoryPath: Path): Int {
        return Files.list(directoryPath)
            .count()
            .toInt()
    }
}
package org.zooplus.anagrams.component.io.resources.dictionary.reader

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.zooplus.anagrams.component.io.resources.dictionary.reader.impl.DictionaryTotalReader
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

internal class DictionaryTotalReaderTest {
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
    fun `should return all words from all files in the directory`() {
        val dirPath = fs.getPath("/test")
        val file1 = dirPath.resolve("file1.txt")
        val file2 = dirPath.resolve("file2.txt")

        Files.write(file1, listOf("hello", "world"))
        Files.write(file2, listOf("test", "input"))

        val dictionaryProperties: DictionaryProperties = mock()
        whenever(dictionaryProperties.dictionaryPath).thenReturn(dirPath.toString())

        val reader = DictionaryTotalReader(fs, dictionaryProperties)
        val directoryContent = reader.getFromDirectory()

        val expectedWords = listOf("hello", "world", "test", "input")
        assertEquals(expectedWords, directoryContent.dictionaryContent)

    }

    @Test
    fun `should throw an exception when the directory does not exist`() {
        val nonExistentPath = "/nonexistent"

        val dictionaryProperties: DictionaryProperties = mock()
        whenever(dictionaryProperties.dictionaryPath).thenReturn(nonExistentPath)

        val reader = DictionaryTotalReader(fs, dictionaryProperties)

        assertThrows<java.nio.file.NoSuchFileException> {
            reader.getFromDirectory()
        }
    }
}
package org.zooplus.anagrams.component.io.resources.dictionary.writter.impl

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.service.io.resources.ResourcesService
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

@ExtendWith(MockitoExtension::class)
internal class DictionaryAdderTest {

    private lateinit var fs: FileSystem
    private lateinit var dirPath: Path
    @Mock
    private lateinit var resourcesService: ResourcesService

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
        val newDirRelPath = "/dictionary/new.txt"
        val dictionaryProperties: DictionaryProperties = mock()
        whenever(dictionaryProperties.dictionaryDirPath).thenReturn(dirPath.toString())

        DictionaryAdder(fs, dictionaryProperties, resourcesService).write(
            listOf("hello", "input", "test", "world"),
            newDirRelPath
        )

        val expectedContent = listOf("hello", "input", "test", "world")
        val content = Files.readAllLines(dirPath.resolve(newDirRelPath))

        verify(resourcesService).deleteExisting(dirPath.resolve(newDirRelPath).parent)
        assertEquals(1, countFilesInDirectory(dirPath.resolve(newDirRelPath)))
        assertEquals(expectedContent, content)
    }

    private fun countFilesInDirectory(dictionaryPath: Path): Int {
        return Files.list(dictionaryPath.parent)
            .count()
            .toInt()
    }
}
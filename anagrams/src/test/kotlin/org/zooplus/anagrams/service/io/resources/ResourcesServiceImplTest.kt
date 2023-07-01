package org.zooplus.anagrams.service.io.resources

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.zooplus.anagrams.component.io.resources.dictionary.writter.impl.DictionaryOverwritter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

internal class ResourcesServiceImplTest {

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
    fun deleteExisting() {
        val dirPath = fs.getPath("/test")

        val file1 = dirPath.resolve("file1.txt")
        val file2 = dirPath.resolve("file2.txt")

        Files.write(file1, listOf("hello", "world"))
        Files.write(file2, listOf("test", "input"))

        ResourcesServiceImpl().deleteExisting(dirPath)

        assertEquals(0, countFilesInDirectory(dirPath))
    }

    private fun countFilesInDirectory(directoryPath: Path): Int {
        return Files.list(directoryPath)
            .count()
            .toInt()
    }
}
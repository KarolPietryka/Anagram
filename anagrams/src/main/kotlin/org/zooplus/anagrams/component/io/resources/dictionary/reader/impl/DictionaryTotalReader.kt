package org.zooplus.anagrams.component.io.resources.dictionary.reader.impl

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import java.nio.file.FileSystem
import java.nio.file.Files

@Primary
@Component()
class DictionaryTotalReader constructor(
    private val fs: FileSystem,
    private val dictionaryProperties: DictionaryProperties,
    ): DictionaryReader {
    override fun getFromDirectory(): DirectoryContent {
        val path = fs.getPath(dictionaryProperties.dictionaryDirPath)
        val allLines = mutableListOf<String>()

        Files.walk(path).use { files ->
            files.filter(Files::isRegularFile).forEach { file ->
                allLines.addAll(Files.readAllLines(file))
            }
        }
        return DirectoryContent(allLines)
    }
}
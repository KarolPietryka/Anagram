package org.zooplus.anagrams.component.io.resources.dictionary.impl

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import org.zooplus.anagrams.model.io.dictionary.trance.DictionaryTranceEntity
import java.nio.file.Files
import java.nio.file.Paths

@Primary
@Component("dictionaryTotalReader")
class DictionaryTotalReader constructor(
    private val dictionaryProperties: DictionaryProperties,
    ): DictionaryReader {
    override fun getFromDirectory(): DirectoryContent {
        val path = Paths.get(dictionaryProperties.dictionaryPath)
        val allLines = mutableListOf<String>()

        Files.walk(path).use { files ->
            files.filter(Files::isRegularFile).forEach { file ->
                allLines.addAll(Files.readAllLines(file))
            }
        }
        return DirectoryContent(allLines)
    }
}
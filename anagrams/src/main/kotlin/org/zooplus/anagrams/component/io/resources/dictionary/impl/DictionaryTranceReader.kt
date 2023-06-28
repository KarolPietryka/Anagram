package org.zooplus.anagrams.component.io.resources.dictionary.impl

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.zooplus.anagrams.component.io.resources.dictionary.DictionaryReader
import org.zooplus.anagrams.config.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.model.io.dictionary.DirectoryContent
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths

@Component
class DictionaryTranceReader constructor(
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

        //return allLines
        return DirectoryContent(emptyList())
    }
}
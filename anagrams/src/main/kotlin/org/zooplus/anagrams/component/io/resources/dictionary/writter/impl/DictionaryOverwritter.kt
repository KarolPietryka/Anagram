package org.zooplus.anagrams.component.io.resources.dictionary.writter.impl

import org.springframework.stereotype.Component
import org.zooplus.anagrams.component.io.resources.dictionary.writter.DictionaryWriter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.service.io.resources.ResourcesService
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

@Component
class DictionaryOverwritter(
    private val fs: FileSystem,
    private val dictionaryProperties: DictionaryProperties,
    private val resourcesService: ResourcesService
): DictionaryWriter {
    override fun write(dictionaryContent: List<String>, newDictionaryPath: String): Path {
        val dictionaryDir = fs.getPath(dictionaryProperties.dictionaryDirPath)
        resourcesService.deleteExisting(dictionaryDir)
        writeIntoDictionary(dictionaryDir.resolve(newDictionaryPath),  dictionaryContent)
        return dictionaryDir.resolve(newDictionaryPath)
    }

    private fun writeIntoDictionary(dictionaryFilePath: Path, words: List<String>) {
        Files.createFile(dictionaryFilePath)
        Files.write(dictionaryFilePath, words)
    }

}
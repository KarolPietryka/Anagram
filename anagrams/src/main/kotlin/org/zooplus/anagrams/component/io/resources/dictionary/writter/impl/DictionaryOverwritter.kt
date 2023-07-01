package org.zooplus.anagrams.component.io.resources.dictionary.writter.impl

import org.springframework.stereotype.Component
import org.zooplus.anagrams.component.io.resources.dictionary.writter.DictionaryWriter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

@Component
class DictionaryOverwritter(
    private val fs: FileSystem,
    private val dictionaryProperties: DictionaryProperties,
): DictionaryWriter {
    override fun write(dictionaryContent: List<String>, newDictionaryName: String) {
        val dictionaryDir = fs.getPath(dictionaryProperties.dictionaryDirPath)
        deleteExisting(dictionaryDir)
        writeIntoDictionary(dictionaryDir.resolve(newDictionaryName),  dictionaryContent)
    }
    private fun deleteExisting(dictionaryDir: Path) =
        Files.walk(dictionaryDir)
            .sorted(Comparator.reverseOrder())
            .filter { it != dictionaryDir }
            .forEach(Files::delete)

    private fun writeIntoDictionary(dictionaryFilePath: Path, words: List<String>) {
        Files.createFile(dictionaryFilePath)
        Files.write(dictionaryFilePath, words)
    }

}
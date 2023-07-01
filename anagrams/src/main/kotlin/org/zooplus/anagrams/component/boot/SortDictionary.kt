package org.zooplus.anagrams.component.boot

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.component.io.resources.dictionary.writter.DictionaryWriter
import org.zooplus.anagrams.config.props.io.resources.dictionary.DictionaryProperties
import org.zooplus.anagrams.service.io.resources.ResourcesService
import java.nio.file.Path
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
@Order(1)
@ConditionalOnProperty(value = ["app.zooplus.anagram.searcher.type"], havingValue = "sorted")
@Profile("!test")
class SortDictionary(
    private val dictionaryReader: DictionaryReader,
    @Qualifier("dictionaryAdder")
    private val dictionaryWriter: DictionaryWriter,
    private val dictionaryProperties: DictionaryProperties,
    private val resourcesService: ResourcesService,
): ApplicationRunner {
    private var createdSortedDir: Path? = null
    override fun run(args: ApplicationArguments?) {
        val sortedContent = dictionaryReader.getFromDirectory().dictionaryContent.sorted()
        createdSortedDir = dictionaryWriter.write(sortedContent, dictionaryProperties.sortedDictionaryPath)
    }
    @PostConstruct
    fun registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(Thread(this::cleanup))
    }
    @PreDestroy
    private fun cleanup() {
        createdSortedDir?.let { resourcesService.deleteExisting(it.parent) }
    }
}
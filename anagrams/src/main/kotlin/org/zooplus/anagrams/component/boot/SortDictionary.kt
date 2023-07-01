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

@Component
@Order(1)
@ConditionalOnProperty(value = ["app.zooplus.anagram.searcher.type"], havingValue = "sorted")
@Profile("!test")
class SortDictionary(
    private val dictionaryReader: DictionaryReader,
    @Qualifier("sortedDictionaryWriter")
    private val dictionaryWriter: DictionaryWriter,
    private val dictionaryProperties: DictionaryProperties,
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val sortedContent = dictionaryReader.getFromDirectory().dictionaryContent.sorted()
        dictionaryWriter.write(sortedContent, dictionaryProperties.sortedDictionaryFileName)
    }
}
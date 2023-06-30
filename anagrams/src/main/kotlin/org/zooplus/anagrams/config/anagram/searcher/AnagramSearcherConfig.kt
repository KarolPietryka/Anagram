package org.zooplus.anagrams.config.anagram.searcher

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zooplus.anagrams.component.io.resources.dictionary.reader.DictionaryReader
import org.zooplus.anagrams.service.anagram.searcher.AnagramSearcher
import org.zooplus.anagrams.service.anagram.searcher.impl.SortedDictionaryAnagramSearcher
import org.zooplus.anagrams.service.anagram.searcher.impl.UnsortedDictionaryAnagramSearcher

@Configuration
open class AnagramSearcherConfig {
    @Bean
    @ConditionalOnProperty(value = ["app.zooplus.anagram.searcher.type"], havingValue = "unsorted", matchIfMissing = true)
    open fun unsortedDictionaryAnagramSearcher(directoryReader: DictionaryReader): AnagramSearcher {
        return UnsortedDictionaryAnagramSearcher(directoryReader)
    }

    @Bean
    @ConditionalOnProperty(value = ["app.zooplus.anagram.searcher.type"], havingValue = "sorted")
    open fun sortedAnagramSearcher(directoryReader: DictionaryReader): AnagramSearcher {
        return SortedDictionaryAnagramSearcher(directoryReader)
    }}
package org.zooplus.anagrams.config.props.io.resources.dictionary

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.zooplus.resources.dictionary", ignoreUnknownFields = true)
open class DictionaryProperties {
     open var dictionaryDirPath: String = ""
     open var sortedDictionaryPath: String = ""
}
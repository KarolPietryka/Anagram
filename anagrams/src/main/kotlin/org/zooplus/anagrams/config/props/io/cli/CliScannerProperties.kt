package org.zooplus.anagrams.config.props.io.cli

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.zooplus.cli", ignoreUnknownFields = true)
class CliScannerProperties {
    var textArt: Boolean = false
}
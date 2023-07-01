package org.zooplus.anagrams.service.io.resources

import java.nio.file.Path

interface ResourcesService {
    fun deleteExisting(dictionaryDir: Path)
}
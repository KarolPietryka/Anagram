package org.zooplus.anagrams.service.io.resources

import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path

@Service
class ResourcesServiceImpl:  ResourcesService{
    override fun deleteExisting(dictionaryDir: Path) =
        Files.walk(dictionaryDir)
            .sorted(Comparator.reverseOrder())
            .filter { it != dictionaryDir }
            .forEach(Files::delete)
}
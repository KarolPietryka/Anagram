package org.zooplus.anagrams.config.io.resources

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import com.google.common.jimfs.Jimfs

@Configuration
open class FileSystemConfig {

    @Bean
    @Profile("!test")
    open fun prodFileSystem(): FileSystem = FileSystems.getDefault()

    @Bean
    @Profile("test")
    open fun testFileSystem(): FileSystem = Jimfs.newFileSystem(com.google.common.jimfs.Configuration.unix())
}
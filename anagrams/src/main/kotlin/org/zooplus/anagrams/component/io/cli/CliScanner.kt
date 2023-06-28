package org.zooplus.anagrams.component.io.cli

import org.springframework.stereotype.Component
import org.zooplus.anagrams.config.CliScannerProperties
import java.util.*

@Component
class CliScanner constructor(
    private val cliScannerProperties: CliScannerProperties
){
    fun scan(){
        val scanner = Scanner(System.`in`)
        println("Enter world for anagram")
        val input = scanner.nextLine()
    }
}
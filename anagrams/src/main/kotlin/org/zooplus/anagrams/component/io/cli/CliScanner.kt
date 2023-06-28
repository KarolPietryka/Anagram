package org.zooplus.anagrams.component.io.cli

import org.springframework.stereotype.Component
import org.zooplus.anagrams.config.io.cli.CliScannerProperties
import org.zooplus.anagrams.service.anagram.AnagramCalculator
import java.util.*

@Component
class CliScanner constructor(
    private val cliScannerProperties: CliScannerProperties,
    private val anagramCalculator: AnagramCalculator
){
    fun scan(){
        val scanner = Scanner(System.`in`)
        println("Enter world for anagram")
        val input = scanner.nextLine()
        anagramCalculator.getAnagrams(input)
    }
}
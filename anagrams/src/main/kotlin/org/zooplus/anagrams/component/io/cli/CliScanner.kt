package org.zooplus.anagrams.component.io.cli

import org.springframework.stereotype.Component
import org.zooplus.anagrams.config.props.io.cli.CliScannerProperties
import org.zooplus.anagrams.service.anagram.searcher.AnagramSearcher
import java.util.*

@Component
class CliScanner constructor(
    private val cliScannerProperties: CliScannerProperties,
    private val anagramSearcher: AnagramSearcher
){
    fun scan(){
        while(true) {
            val scanner = Scanner(System.`in`)
            println("Enter world for anagram")
            val input = scanner.nextLine()
            anagramSearcher.getAnagram(input)?.let {
                println("Anagram of word $input found in dictionaries is $it")
            } ?: println("No anagram of word $input found")
        }
    }
}
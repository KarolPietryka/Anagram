package org.zooplus.anagrams.service.anagram

interface AnagramCalculator {
    fun getAnagrams(input: String): List<String>
}
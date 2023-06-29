package org.zooplus.anagrams.service.anagram

interface AnagramSearcher {
    fun getAnagram(input: String): String?
}
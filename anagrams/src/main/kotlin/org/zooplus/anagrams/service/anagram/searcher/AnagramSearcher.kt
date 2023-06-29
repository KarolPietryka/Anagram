package org.zooplus.anagrams.service.anagram.searcher

interface AnagramSearcher {
    fun getAnagram(input: String): String?
}
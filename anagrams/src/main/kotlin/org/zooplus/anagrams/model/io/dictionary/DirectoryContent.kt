package org.zooplus.anagrams.model.io.dictionary


data class DirectoryContent(
    val dictionaryContent: List<String>
){
    val size = dictionaryContent.size
}
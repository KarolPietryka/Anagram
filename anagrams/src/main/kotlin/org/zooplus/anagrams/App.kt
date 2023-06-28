package org.zooplus.anagrams

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.zooplus.anagrams.component.io.cli.CliScanner

@SpringBootApplication
@ComponentScan("org.zooplus.anagrams")
open class App constructor(
    private val cliScanner: CliScanner
){
    fun boot(){
        cliScanner.scan()
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(App::class.java)
            val app = context.getBean(App::class.java)
            try { app.boot() }
            catch (ex: Exception) { ex.printStackTrace() }
            finally {
                SpringApplication.exit(context)
            }
        }
    }
}


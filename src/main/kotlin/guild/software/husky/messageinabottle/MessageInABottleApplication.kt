package guild.software.husky.messageinabottle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MessageInABottleApplication

fun main(args: Array<String>) {
    runApplication<MessageInABottleApplication>(*args)
}

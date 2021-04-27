package guild.software.husky.messageinabottle.controller.BottleController

import guild.software.husky.messageinabottle.services.BottleService
import guild.software.husky.messageinabottle.services.models.Bottle
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bottle")
class BottleController(private val bottleService: BottleService) {

    @PostMapping("/")
    fun createMessage(@RequestBody messageRequest: MessageRequest) {
        val bottle = Bottle(messageRequest.message)
        bottleService.saveBottle(bottle)
    }

    @GetMapping("/read")
    fun readMessage(): Bottle {
        return bottleService.retrieveBottle()
    }
}
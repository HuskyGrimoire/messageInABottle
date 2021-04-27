package guild.software.husky.messageinabottle.controller.BottleController

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException

@ControllerAdvice
class RestExceptiionAdvice {

    @ExceptionHandler(HttpClientErrorException::class)
    fun handleException(exception: HttpClientErrorException): ResponseEntity<String> {
        return ResponseEntity(exception.statusText, exception.statusCode)
    }
}



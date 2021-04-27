package guild.software.husky.messageinabottle.services

import guild.software.husky.messageinabottle.repositories.BottleRepository
import guild.software.husky.messageinabottle.repositories.entities.BottleEntity
import guild.software.husky.messageinabottle.services.models.Bottle
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.context.request.RequestContextHolder
import java.util.*
import javax.transaction.Transactional

@Service
class BottleService(private val bottleRepository: BottleRepository) {

    fun saveBottle(bottle: Bottle) {
        val creatorId = getSessionUuid()
        val bottleEntity = BottleEntity()
        bottleEntity.creatorId = creatorId
        bottleEntity.message = bottle.message
        bottleEntity.id = UUID.randomUUID()
        bottleEntity.viewed = false
        bottleRepository.save(bottleEntity)
    }

    @Transactional
    fun retrieveBottle(): Bottle {
        val sessionId = getSessionUuid()
        try {
            val bottleEntity = bottleRepository.markBottleAsViewed(sessionId)
            return Bottle(bottleEntity.message!!)
        } catch (e: EmptyResultDataAccessException) {
            throw HttpClientErrorException(HttpStatus.NO_CONTENT, "No more messages in the digital sea for you to read. Please try again later.")
        }
    }

    private fun getSessionUuid(): String {
        return RequestContextHolder.currentRequestAttributes().sessionId
    }
}
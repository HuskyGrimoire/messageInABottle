package guild.software.husky.messageinabottle.services

import guild.software.husky.messageinabottle.repositories.BottleRepository
import guild.software.husky.messageinabottle.repositories.entities.BottleEntity
import guild.software.husky.messageinabottle.services.models.Bottle
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import java.util.*

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

    fun retrieveBottle(): Bottle {
        val sessionId = getSessionUuid()
        val bottleEntities = bottleRepository.findFirstByViewedFalseAndCreatorIdIsNot(sessionId)
        bottleRepository.markBottleAsViewed(bottleEntities.id!!)
        return Bottle(bottleEntities.message!!)
    }

    private fun getSessionUuid(): String {
        return RequestContextHolder.currentRequestAttributes().sessionId
    }
}
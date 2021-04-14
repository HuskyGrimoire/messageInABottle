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
        val bottleEntity = BottleEntity(
                UUID.randomUUID(),
                bottle.message,
                creatorId,
                false
        )
        bottleRepository.save(bottleEntity)
    }

    fun retrieveBottle(): Bottle {
        val sessionId = getSessionUuid()
        val bottleEntities = bottleRepository.findFirstByCreatorIdIsNot(sessionId)
        bottleRepository.deleteById(bottleEntities.id)
        return Bottle(bottleEntities.message)
    }

    private fun getSessionUuid():UUID{
        return UUID.fromString(RequestContextHolder.currentRequestAttributes().sessionId);
    }
}
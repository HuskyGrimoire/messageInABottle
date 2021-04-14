package guild.software.husky.messageinabottle.repositories

import guild.software.husky.messageinabottle.repositories.entities.BottleEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BottleRepository : CrudRepository<BottleEntity, UUID> {

    fun findFirstByViewedAndCreatorIdIsNot(viewed: Boolean, creatorId: UUID): BottleEntity
}


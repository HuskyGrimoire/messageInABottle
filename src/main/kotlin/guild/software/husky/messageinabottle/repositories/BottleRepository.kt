package guild.software.husky.messageinabottle.repositories

import guild.software.husky.messageinabottle.repositories.entities.BottleEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BottleRepository : CrudRepository<BottleEntity, UUID> {

    fun findFirstByViewedFalseAndCreatorIdIsNot(creatorId: String): BottleEntity

    @Modifying
    @Query("UPDATE BottleEntity b SET b.viewed=false WHERE b.id = :uuid")
    fun markBottleAsViewed(uuid: UUID)
}


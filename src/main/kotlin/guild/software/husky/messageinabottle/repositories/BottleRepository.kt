package guild.software.husky.messageinabottle.repositories

import guild.software.husky.messageinabottle.repositories.entities.BottleEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BottleRepository : CrudRepository<BottleEntity, UUID> {

    @Query("select b from BottleEntity b where b.viewed = true")
    fun findFirstByViewedFalseAndCreatorIdIsNot(creatorId: String): BottleEntity

    @Query(nativeQuery = true, value = "UPDATE public.bottles " +
            "set viewed = true " +
            "WHERE id = (" +
            "SELECT id from public.bottles " +
            "WHERE creator_id != :creatorid " +
            "and viewed != true " +
            "ORDER BY id LIMIT 1" +
            ") " +
            "returning *;")
    fun markBottleAsViewed(@Param("creatorid") creatorid: String): BottleEntity
}


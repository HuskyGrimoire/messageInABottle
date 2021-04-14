package guild.software.husky.messageinabottle.repositories.entities

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.With
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.*

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Bottles")
data class BottleEntity(
        @Indexed val id: UUID,
        val message: String,
        @Indexed val creatorId: UUID,
        val viewed: Boolean
)

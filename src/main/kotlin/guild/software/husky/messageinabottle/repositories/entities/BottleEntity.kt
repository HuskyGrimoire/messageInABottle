package guild.software.husky.messageinabottle.repositories.entities

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.With
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*
import javax.persistence.Column

//@Entity
//@Table(name = "bottles")
//class BottleEntity(
//        @Id val id: UUID,
//        val message: String,
//        val creatorId: String,
//        val viewed: Boolean
//)
//@Entity
//@Table(name = "bottles")
//data class BottleEntity(
//        @get:Id
//        @get:Column(name = "id")
//        val id: UUID,
//        val message: String,
//        val creatorId: String,
//        val viewed: Boolean
//)


@Data
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bottles")
open class BottleEntity {

    @get:Id
    open var id: UUID? = null

    @get:Column(name = "message")
    open var message: String? = null

    @get:Column(name = "creatorId")
    open var creatorId: String? = null

    @get:Column(name = "viewed")
    open var viewed: Boolean? = null


}
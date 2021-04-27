package guild.software.husky.messageinabottle.controller.BottleController

import lombok.Data
import lombok.With

@Data
@With
data class MessageRequest(val message: String)

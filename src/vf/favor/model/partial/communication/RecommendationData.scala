package vf.favor.model.partial.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Represents a recommendation given by a user in order to introduce another user to a third party
  * @param senderId Id of the user doing the recommending
  * @param recipientId Id of the user receiving the recommendation
  * @param recommendedId Id of the user being recommended
  * @param message Message sent along with the recommendation
  * @param created Time when this recommendation was sent
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RecommendationData(senderId: Int, recipientId: Int, recommendedId: Int, message: String, 
	created: Instant = Instant.now()) 
	extends ModelConvertible
{
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("sender_id" -> senderId, "recipient_id" -> recipientId, 
			"recommended_id" -> recommendedId, "message" -> message, "created" -> created))
}


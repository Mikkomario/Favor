package vf.favor.model.partial.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Records the response given by the recommendation recipient
  * @param recommendationId Id of the recommendation this response is for
  * @param newTrustLevel Granted level of trust from the person receiving the recommendation. None if no trust was granted.
  * @param message Written response to the recommendation
  * @param created Time when this RecommendationResponse was first created
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RecommendationResponseData(recommendationId: Int, newTrustLevel: Int, message: String, 
	created: Instant = Instant.now()) 
	extends ModelConvertible
{
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("recommendation_id" -> recommendationId, "new_trust_level" -> newTrustLevel, 
			"message" -> message, "created" -> created))
}


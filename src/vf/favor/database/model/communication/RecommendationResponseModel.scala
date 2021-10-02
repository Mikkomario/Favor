package vf.favor.database.model.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import vf.favor.database.factory.communication.RecommendationResponseFactory
import vf.favor.model.partial.communication.RecommendationResponseData
import vf.favor.model.stored.communication.RecommendationResponse

/**
  * Used for constructing RecommendationResponseModel instances and for inserting RecommendationResponses to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RecommendationResponseModel 
	extends DataInserter[RecommendationResponseModel, RecommendationResponse, RecommendationResponseData]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains RecommendationResponse recommendationId
	  */
	val recommendationIdAttName = "recommendationId"
	
	/**
	  * Name of the property that contains RecommendationResponse newTrustLevel
	  */
	val newTrustLevelAttName = "newTrustLevel"
	
	/**
	  * Name of the property that contains RecommendationResponse message
	  */
	val messageAttName = "message"
	
	/**
	  * Name of the property that contains RecommendationResponse created
	  */
	val createdAttName = "created"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains RecommendationResponse recommendationId
	  */
	def recommendationIdColumn = table(recommendationIdAttName)
	
	/**
	  * Column that contains RecommendationResponse newTrustLevel
	  */
	def newTrustLevelColumn = table(newTrustLevelAttName)
	
	/**
	  * Column that contains RecommendationResponse message
	  */
	def messageColumn = table(messageAttName)
	
	/**
	  * Column that contains RecommendationResponse created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = RecommendationResponseFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: RecommendationResponseData) = 
		apply(None, Some(data.recommendationId), Some(data.newTrustLevel), Some(data.message), 
			Some(data.created))
	
	override def complete(id: Value, data: RecommendationResponseData) = RecommendationResponse(id.getInt, 
		data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this RecommendationResponse was first created
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param id A RecommendationResponse id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param message Written response to the recommendation
	  * @return A model containing only the specified message
	  */
	def withMessage(message: String) = apply(message = Some(message))
	
	/**
	  * @param newTrustLevel Granted level of trust from the person receiving the recommendation. None if no trust was granted.
	  * @return A model containing only the specified newTrustLevel
	  */
	def withNewTrustLevel(newTrustLevel: Int) = apply(newTrustLevel = Some(newTrustLevel))
	
	/**
	  * @param recommendationId Id of the recommendation this response is for
	  * @return A model containing only the specified recommendationId
	  */
	def withRecommendationId(recommendationId: Int) = apply(recommendationId = Some(recommendationId))
}

/**
  * Used for interacting with RecommendationResponses in the database
  * @param id RecommendationResponse database id
  * @param recommendationId Id of the recommendation this response is for
  * @param newTrustLevel Granted level of trust from the person receiving the recommendation. None if no trust was granted.
  * @param message Written response to the recommendation
  * @param created Time when this RecommendationResponse was first created
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RecommendationResponseModel(id: Option[Int] = None, recommendationId: Option[Int] = None, 
	newTrustLevel: Option[Int] = None, message: Option[String] = None, created: Option[Instant] = None) 
	extends StorableWithFactory[RecommendationResponse]
{
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationResponseModel.factory
	
	override def valueProperties = 
	{
		import RecommendationResponseModel._
		Vector("id" -> id, recommendationIdAttName -> recommendationId, 
			newTrustLevelAttName -> newTrustLevel, messageAttName -> message, createdAttName -> created)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param message A new message
	  * @return A new copy of this model with the specified message
	  */
	def withMessage(message: String) = copy(message = Some(message))
	
	/**
	  * @param newTrustLevel A new newTrustLevel
	  * @return A new copy of this model with the specified newTrustLevel
	  */
	def withNewTrustLevel(newTrustLevel: Int) = copy(newTrustLevel = Some(newTrustLevel))
	
	/**
	  * @param recommendationId A new recommendationId
	  * @return A new copy of this model with the specified recommendationId
	  */
	def withRecommendationId(recommendationId: Int) = copy(recommendationId = Some(recommendationId))
}


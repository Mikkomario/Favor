package vf.favor.database.model.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import vf.favor.database.factory.communication.RecommendationFactory
import vf.favor.model.partial.communication.RecommendationData
import vf.favor.model.stored.communication.Recommendation

/**
  * Used for constructing RecommendationModel instances and for inserting Recommendations to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RecommendationModel extends DataInserter[RecommendationModel, Recommendation, RecommendationData]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains Recommendation senderId
	  */
	val senderIdAttName = "senderId"
	
	/**
	  * Name of the property that contains Recommendation recipientId
	  */
	val recipientIdAttName = "recipientId"
	
	/**
	  * Name of the property that contains Recommendation recommendedId
	  */
	val recommendedIdAttName = "recommendedId"
	
	/**
	  * Name of the property that contains Recommendation message
	  */
	val messageAttName = "message"
	
	/**
	  * Name of the property that contains Recommendation created
	  */
	val createdAttName = "created"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains Recommendation senderId
	  */
	def senderIdColumn = table(senderIdAttName)
	
	/**
	  * Column that contains Recommendation recipientId
	  */
	def recipientIdColumn = table(recipientIdAttName)
	
	/**
	  * Column that contains Recommendation recommendedId
	  */
	def recommendedIdColumn = table(recommendedIdAttName)
	
	/**
	  * Column that contains Recommendation message
	  */
	def messageColumn = table(messageAttName)
	
	/**
	  * Column that contains Recommendation created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = RecommendationFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: RecommendationData) = 
		apply(None, Some(data.senderId), Some(data.recipientId), Some(data.recommendedId), 
			Some(data.message), Some(data.created))
	
	override def complete(id: Value, data: RecommendationData) = Recommendation(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this recommendation was sent
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param id A Recommendation id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param message Message sent along with the recommendation
	  * @return A model containing only the specified message
	  */
	def withMessage(message: String) = apply(message = Some(message))
	
	/**
	  * @param recipientId Id of the user receiving the recommendation
	  * @return A model containing only the specified recipientId
	  */
	def withRecipientId(recipientId: Int) = apply(recipientId = Some(recipientId))
	
	/**
	  * @param recommendedId Id of the user being recommended
	  * @return A model containing only the specified recommendedId
	  */
	def withRecommendedId(recommendedId: Int) = apply(recommendedId = Some(recommendedId))
	
	/**
	  * @param senderId Id of the user doing the recommending
	  * @return A model containing only the specified senderId
	  */
	def withSenderId(senderId: Int) = apply(senderId = Some(senderId))
}

/**
  * Used for interacting with Recommendations in the database
  * @param id Recommendation database id
  * @param senderId Id of the user doing the recommending
  * @param recipientId Id of the user receiving the recommendation
  * @param recommendedId Id of the user being recommended
  * @param message Message sent along with the recommendation
  * @param created Time when this recommendation was sent
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RecommendationModel(id: Option[Int] = None, senderId: Option[Int] = None, 
	recipientId: Option[Int] = None, recommendedId: Option[Int] = None, message: Option[String] = None, 
	created: Option[Instant] = None) 
	extends StorableWithFactory[Recommendation]
{
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationModel.factory
	
	override def valueProperties = 
	{
		import RecommendationModel._
		Vector("id" -> id, senderIdAttName -> senderId, recipientIdAttName -> recipientId, 
			recommendedIdAttName -> recommendedId, messageAttName -> message, createdAttName -> created)
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
	  * @param recipientId A new recipientId
	  * @return A new copy of this model with the specified recipientId
	  */
	def withRecipientId(recipientId: Int) = copy(recipientId = Some(recipientId))
	
	/**
	  * @param recommendedId A new recommendedId
	  * @return A new copy of this model with the specified recommendedId
	  */
	def withRecommendedId(recommendedId: Int) = copy(recommendedId = Some(recommendedId))
	
	/**
	  * @param senderId A new senderId
	  * @return A new copy of this model with the specified senderId
	  */
	def withSenderId(senderId: Int) = copy(senderId = Some(senderId))
}


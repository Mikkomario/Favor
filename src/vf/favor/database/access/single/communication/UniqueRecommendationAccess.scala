package vf.favor.database.access.single.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.RecommendationFactory
import vf.favor.database.model.communication.RecommendationModel
import vf.favor.model.stored.communication.Recommendation

/**
  * A common trait for access points that return individual and distinct Recommendations.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueRecommendationAccess 
	extends SingleRowModelAccess[Recommendation] 
		with DistinctModelAccess[Recommendation, Option[Recommendation], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the user doing the recommending. None if no instance (or value) was found.
	  */
	def senderId(implicit connection: Connection) = pullColumn(model.senderIdColumn).int
	
	/**
	  * Id of the user receiving the recommendation. None if no instance (or value) was found.
	  */
	def recipientId(implicit connection: Connection) = pullColumn(model.recipientIdColumn).int
	
	/**
	  * Id of the user being recommended. None if no instance (or value) was found.
	  */
	def recommendedId(implicit connection: Connection) = pullColumn(model.recommendedIdColumn).int
	
	/**
	  * Message sent along with the recommendation. None if no instance (or value) was found.
	  */
	def message(implicit connection: Connection) = pullColumn(model.messageColumn).string
	
	/**
	  * Time when this recommendation was sent. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted Recommendation instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any Recommendation instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the message of the targeted Recommendation instance(s)
	  * @param newMessage A new message to assign
	  * @return Whether any Recommendation instance was affected
	  */
	def message_=(newMessage: String)(implicit connection: Connection) = 
		putColumn(model.messageColumn, newMessage)
	
	/**
	  * Updates the recipientId of the targeted Recommendation instance(s)
	  * @param newRecipientId A new recipientId to assign
	  * @return Whether any Recommendation instance was affected
	  */
	def recipientId_=(newRecipientId: Int)(implicit connection: Connection) = 
		putColumn(model.recipientIdColumn, newRecipientId)
	
	/**
	  * Updates the recommendedId of the targeted Recommendation instance(s)
	  * @param newRecommendedId A new recommendedId to assign
	  * @return Whether any Recommendation instance was affected
	  */
	def recommendedId_=(newRecommendedId: Int)(implicit connection: Connection) = 
		putColumn(model.recommendedIdColumn, newRecommendedId)
	
	/**
	  * Updates the senderId of the targeted Recommendation instance(s)
	  * @param newSenderId A new senderId to assign
	  * @return Whether any Recommendation instance was affected
	  */
	def senderId_=(newSenderId: Int)(implicit connection: Connection) = 
		putColumn(model.senderIdColumn, newSenderId)
}


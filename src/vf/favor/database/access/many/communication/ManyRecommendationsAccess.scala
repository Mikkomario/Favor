package vf.favor.database.access.many.communication

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.RecommendationFactory
import vf.favor.database.model.communication.RecommendationModel
import vf.favor.model.stored.communication.Recommendation

/**
  * A common trait for access points which target multiple Recommendations at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyRecommendationsAccess extends ManyRowModelAccess[Recommendation] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * senderIds of the accessible Recommendations
	  */
	def senderIds(implicit connection: Connection) = 
		pullColumn(model.senderIdColumn).flatMap { value => value.int }
	
	/**
	  * recipientIds of the accessible Recommendations
	  */
	def recipientIds(implicit connection: Connection) = 
		pullColumn(model.recipientIdColumn).flatMap { value => value.int }
	
	/**
	  * recommendedIds of the accessible Recommendations
	  */
	def recommendedIds(implicit connection: Connection) = 
		pullColumn(model.recommendedIdColumn).flatMap { value => value.int }
	
	/**
	  * messages of the accessible Recommendations
	  */
	def messages(implicit connection: Connection) = 
		pullColumn(model.messageColumn).flatMap { value => value.string }
	
	/**
	  * createds of the accessible Recommendations
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


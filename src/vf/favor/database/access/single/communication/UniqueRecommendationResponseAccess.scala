package vf.favor.database.access.single.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.RecommendationResponseFactory
import vf.favor.database.model.communication.RecommendationResponseModel
import vf.favor.model.stored.communication.RecommendationResponse

/**
  * A common trait for access points that return individual and distinct RecommendationResponses.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueRecommendationResponseAccess 
	extends SingleRowModelAccess[RecommendationResponse] 
		with DistinctModelAccess[RecommendationResponse, Option[RecommendationResponse], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the recommendation this response is for. None if no instance (or value) was found.
	  */
	def recommendationId(implicit connection: Connection) = pullColumn(model.recommendationIdColumn).int
	
	/**
	  * Granted level of trust from the person receiving the recommendation. None if no trust was granted.. None if no instance (or value) was found.
	  */
	def newTrustLevel(implicit connection: Connection) = pullColumn(model.newTrustLevelColumn).int
	
	/**
	  * Written response to the recommendation. None if no instance (or value) was found.
	  */
	def message(implicit connection: Connection) = pullColumn(model.messageColumn).string
	
	/**
	  * Time when this RecommendationResponse was first created. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationResponseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationResponseFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted RecommendationResponse instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any RecommendationResponse instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the message of the targeted RecommendationResponse instance(s)
	  * @param newMessage A new message to assign
	  * @return Whether any RecommendationResponse instance was affected
	  */
	def message_=(newMessage: String)(implicit connection: Connection) = 
		putColumn(model.messageColumn, newMessage)
	
	/**
	  * Updates the newTrustLevel of the targeted RecommendationResponse instance(s)
	  * @param newNewTrustLevel A new newTrustLevel to assign
	  * @return Whether any RecommendationResponse instance was affected
	  */
	def newTrustLevel_=(newNewTrustLevel: Int)(implicit connection: Connection) = 
		putColumn(model.newTrustLevelColumn, newNewTrustLevel)
	
	/**
	  * Updates the recommendationId of the targeted RecommendationResponse instance(s)
	  * @param newRecommendationId A new recommendationId to assign
	  * @return Whether any RecommendationResponse instance was affected
	  */
	def recommendationId_=(newRecommendationId: Int)(implicit connection: Connection) = 
		putColumn(model.recommendationIdColumn, newRecommendationId)
}


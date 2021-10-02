package vf.favor.database.access.many.communication

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.RecommendationResponseFactory
import vf.favor.database.model.communication.RecommendationResponseModel
import vf.favor.model.stored.communication.RecommendationResponse

/**
  * A common trait for access points which target multiple RecommendationResponses at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyRecommendationResponsesAccess extends ManyRowModelAccess[RecommendationResponse] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * recommendationIds of the accessible RecommendationResponses
	  */
	def recommendationIds(implicit connection: Connection) = 
		pullColumn(model.recommendationIdColumn).flatMap { value => value.int }
	
	/**
	  * newTrustLevels of the accessible RecommendationResponses
	  */
	def newTrustLevels(implicit connection: Connection) = 
		pullColumn(model.newTrustLevelColumn).flatMap { value => value.int }
	
	/**
	  * messages of the accessible RecommendationResponses
	  */
	def messages(implicit connection: Connection) = 
		pullColumn(model.messageColumn).flatMap { value => value.string }
	
	/**
	  * createds of the accessible RecommendationResponses
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationResponseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationResponseFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


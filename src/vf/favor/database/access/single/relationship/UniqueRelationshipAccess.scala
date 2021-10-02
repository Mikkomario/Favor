package vf.favor.database.access.single.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.RelationshipFactory
import vf.favor.database.model.relationship.RelationshipModel
import vf.favor.model.stored.relationship.Relationship

/**
  * A common trait for access points that return individual and distinct Relationships.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueRelationshipAccess 
	extends SingleRowModelAccess[Relationship] 
		with DistinctModelAccess[Relationship, Option[Relationship], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the primary user. None if no instance (or value) was found.
	  */
	def userId(implicit connection: Connection) = pullColumn(model.userIdColumn).int
	
	/**
	  * Id of the user the primary user has a relationship with. None if no instance (or value) was found.
	  */
	def targetUserId(implicit connection: Connection) = pullColumn(model.targetUserIdColumn).int
	
	/**
	  * The primary user's favor towards the target user (cumulative). None if no instance (or value) was found.
	  */
	def currentFavor(implicit connection: Connection) = pullColumn(model.currentFavorColumn).int
	
	/**
	  * Time when this relationship was started. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted Relationship instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any Relationship instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the currentFavor of the targeted Relationship instance(s)
	  * @param newCurrentFavor A new currentFavor to assign
	  * @return Whether any Relationship instance was affected
	  */
	def currentFavor_=(newCurrentFavor: Int)(implicit connection: Connection) = 
		putColumn(model.currentFavorColumn, newCurrentFavor)
	
	/**
	  * Updates the targetUserId of the targeted Relationship instance(s)
	  * @param newTargetUserId A new targetUserId to assign
	  * @return Whether any Relationship instance was affected
	  */
	def targetUserId_=(newTargetUserId: Int)(implicit connection: Connection) = 
		putColumn(model.targetUserIdColumn, newTargetUserId)
	
	/**
	  * Updates the userId of the targeted Relationship instance(s)
	  * @param newUserId A new userId to assign
	  * @return Whether any Relationship instance was affected
	  */
	def userId_=(newUserId: Int)(implicit connection: Connection) = putColumn(model.userIdColumn, newUserId)
}


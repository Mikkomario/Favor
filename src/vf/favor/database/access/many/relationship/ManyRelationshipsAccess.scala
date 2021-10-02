package vf.favor.database.access.many.relationship

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.RelationshipFactory
import vf.favor.database.model.relationship.RelationshipModel
import vf.favor.model.stored.relationship.Relationship

/**
  * A common trait for access points which target multiple Relationships at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyRelationshipsAccess extends ManyRowModelAccess[Relationship] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * userIds of the accessible Relationships
	  */
	def userIds(implicit connection: Connection) = pullColumn(model.userIdColumn)
		.flatMap { value => value.int }
	
	/**
	  * targetUserIds of the accessible Relationships
	  */
	def targetUserIds(implicit connection: Connection) = 
		pullColumn(model.targetUserIdColumn).flatMap { value => value.int }
	
	/**
	  * currentFavor of the accessible Relationships
	  */
	def currentFavor(implicit connection: Connection) = 
		pullColumn(model.currentFavorColumn).flatMap { value => value.int }
	
	/**
	  * createds of the accessible Relationships
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


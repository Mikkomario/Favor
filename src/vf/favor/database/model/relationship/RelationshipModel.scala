package vf.favor.database.model.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import vf.favor.database.factory.relationship.RelationshipFactory
import vf.favor.model.partial.relationship.RelationshipData
import vf.favor.model.stored.relationship.Relationship

/**
  * Used for constructing RelationshipModel instances and for inserting Relationships to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RelationshipModel extends DataInserter[RelationshipModel, Relationship, RelationshipData]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains Relationship userId
	  */
	val userIdAttName = "userId"
	
	/**
	  * Name of the property that contains Relationship targetUserId
	  */
	val targetUserIdAttName = "targetUserId"
	
	/**
	  * Name of the property that contains Relationship currentFavor
	  */
	val currentFavorAttName = "currentFavor"
	
	/**
	  * Name of the property that contains Relationship created
	  */
	val createdAttName = "created"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains Relationship userId
	  */
	def userIdColumn = table(userIdAttName)
	
	/**
	  * Column that contains Relationship targetUserId
	  */
	def targetUserIdColumn = table(targetUserIdAttName)
	
	/**
	  * Column that contains Relationship currentFavor
	  */
	def currentFavorColumn = table(currentFavorAttName)
	
	/**
	  * Column that contains Relationship created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = RelationshipFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: RelationshipData) = 
		apply(None, Some(data.userId), Some(data.targetUserId), Some(data.currentFavor), Some(data.created))
	
	override def complete(id: Value, data: RelationshipData) = Relationship(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this relationship was started
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param currentFavor The primary user's favor towards the target user (cumulative)
	  * @return A model containing only the specified currentFavor
	  */
	def withCurrentFavor(currentFavor: Int) = apply(currentFavor = Some(currentFavor))
	
	/**
	  * @param id A Relationship id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param targetUserId Id of the user the primary user has a relationship with
	  * @return A model containing only the specified targetUserId
	  */
	def withTargetUserId(targetUserId: Int) = apply(targetUserId = Some(targetUserId))
	
	/**
	  * @param userId Id of the primary user
	  * @return A model containing only the specified userId
	  */
	def withUserId(userId: Int) = apply(userId = Some(userId))
}

/**
  * Used for interacting with Relationships in the database
  * @param id Relationship database id
  * @param userId Id of the primary user
  * @param targetUserId Id of the user the primary user has a relationship with
  * @param currentFavor The primary user's favor towards the target user (cumulative)
  * @param created Time when this relationship was started
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RelationshipModel(id: Option[Int] = None, userId: Option[Int] = None, 
	targetUserId: Option[Int] = None, currentFavor: Option[Int] = None, created: Option[Instant] = None) 
	extends StorableWithFactory[Relationship]
{
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipModel.factory
	
	override def valueProperties = 
	{
		import RelationshipModel._
		Vector("id" -> id, userIdAttName -> userId, targetUserIdAttName -> targetUserId, 
			currentFavorAttName -> currentFavor, createdAttName -> created)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param currentFavor A new currentFavor
	  * @return A new copy of this model with the specified currentFavor
	  */
	def withCurrentFavor(currentFavor: Int) = copy(currentFavor = Some(currentFavor))
	
	/**
	  * @param targetUserId A new targetUserId
	  * @return A new copy of this model with the specified targetUserId
	  */
	def withTargetUserId(targetUserId: Int) = copy(targetUserId = Some(targetUserId))
	
	/**
	  * @param userId A new userId
	  * @return A new copy of this model with the specified userId
	  */
	def withUserId(userId: Int) = copy(userId = Some(userId))
}


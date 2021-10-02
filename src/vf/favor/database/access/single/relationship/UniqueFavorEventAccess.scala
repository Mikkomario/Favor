package vf.favor.database.access.single.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.FavorEventFactory
import vf.favor.database.model.relationship.FavorEventModel
import vf.favor.model.stored.relationship.FavorEvent

/**
  * A common trait for access points that return individual and distinct FavorEvents.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueFavorEventAccess 
	extends SingleRowModelAccess[FavorEvent] with DistinctModelAccess[FavorEvent, Option[FavorEvent], Value] 
		with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the relationship in which this even occurred. None if no instance (or value) was found.
	  */
	def relationshipId(implicit connection: Connection) = pullColumn(model.relationshipIdColumn).int
	
	/**
	  * Title of this favor event. None if no instance (or value) was found.
	  */
	def title(implicit connection: Connection) = pullColumn(model.titleColumn).string
	
	/**
	  * More detailed description of this favor event. None if no instance (or value) was found.
	  */
	def description(implicit connection: Connection) = pullColumn(model.descriptionColumn).string
	
	/**
	  * Time when this favor was recognized. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorEventModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorEventFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted FavorEvent instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any FavorEvent instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the description of the targeted FavorEvent instance(s)
	  * @param newDescription A new description to assign
	  * @return Whether any FavorEvent instance was affected
	  */
	def description_=(newDescription: String)(implicit connection: Connection) = 
		putColumn(model.descriptionColumn, newDescription)
	
	/**
	  * Updates the relationshipId of the targeted FavorEvent instance(s)
	  * @param newRelationshipId A new relationshipId to assign
	  * @return Whether any FavorEvent instance was affected
	  */
	def relationshipId_=(newRelationshipId: Int)(implicit connection: Connection) = 
		putColumn(model.relationshipIdColumn, newRelationshipId)
	
	/**
	  * Updates the title of the targeted FavorEvent instance(s)
	  * @param newTitle A new title to assign
	  * @return Whether any FavorEvent instance was affected
	  */
	def title_=(newTitle: String)(implicit connection: Connection) = putColumn(model.titleColumn, newTitle)
}


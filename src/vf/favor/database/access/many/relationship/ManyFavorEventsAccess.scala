package vf.favor.database.access.many.relationship

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.FavorEventFactory
import vf.favor.database.model.relationship.FavorEventModel
import vf.favor.model.stored.relationship.FavorEvent

/**
  * A common trait for access points which target multiple FavorEvents at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyFavorEventsAccess extends ManyRowModelAccess[FavorEvent] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * relationshipIds of the accessible FavorEvents
	  */
	def relationshipIds(implicit connection: Connection) = 
		pullColumn(model.relationshipIdColumn).flatMap { value => value.int }
	
	/**
	  * titles of the accessible FavorEvents
	  */
	def titles(implicit connection: Connection) = pullColumn(model.titleColumn)
		.flatMap { value => value.string }
	
	/**
	  * descriptions of the accessible FavorEvents
	  */
	def descriptions(implicit connection: Connection) = 
		pullColumn(model.descriptionColumn).flatMap { value => value.string }
	
	/**
	  * createds of the accessible FavorEvents
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorEventModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorEventFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


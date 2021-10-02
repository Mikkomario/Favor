package vf.favor.database.model.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import vf.favor.database.factory.relationship.FavorEventFactory
import vf.favor.model.partial.relationship.FavorEventData
import vf.favor.model.stored.relationship.FavorEvent

/**
  * Used for constructing FavorEventModel instances and for inserting FavorEvents to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object FavorEventModel extends DataInserter[FavorEventModel, FavorEvent, FavorEventData]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains FavorEvent relationshipId
	  */
	val relationshipIdAttName = "relationshipId"
	
	/**
	  * Name of the property that contains FavorEvent title
	  */
	val titleAttName = "title"
	
	/**
	  * Name of the property that contains FavorEvent description
	  */
	val descriptionAttName = "description"
	
	/**
	  * Name of the property that contains FavorEvent created
	  */
	val createdAttName = "created"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains FavorEvent relationshipId
	  */
	def relationshipIdColumn = table(relationshipIdAttName)
	
	/**
	  * Column that contains FavorEvent title
	  */
	def titleColumn = table(titleAttName)
	
	/**
	  * Column that contains FavorEvent description
	  */
	def descriptionColumn = table(descriptionAttName)
	
	/**
	  * Column that contains FavorEvent created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = FavorEventFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: FavorEventData) = 
		apply(None, Some(data.relationshipId), Some(data.title), Some(data.description), Some(data.created))
	
	override def complete(id: Value, data: FavorEventData) = FavorEvent(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this favor was recognized
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param description More detailed description of this favor event
	  * @return A model containing only the specified description
	  */
	def withDescription(description: String) = apply(description = Some(description))
	
	/**
	  * @param id A FavorEvent id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param relationshipId Id of the relationship in which this even occurred
	  * @return A model containing only the specified relationshipId
	  */
	def withRelationshipId(relationshipId: Int) = apply(relationshipId = Some(relationshipId))
	
	/**
	  * @param title Title of this favor event
	  * @return A model containing only the specified title
	  */
	def withTitle(title: String) = apply(title = Some(title))
}

/**
  * Used for interacting with FavorEvents in the database
  * @param id FavorEvent database id
  * @param relationshipId Id of the relationship in which this even occurred
  * @param title Title of this favor event
  * @param description More detailed description of this favor event
  * @param created Time when this favor was recognized
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorEventModel(id: Option[Int] = None, relationshipId: Option[Int] = None, 
	title: Option[String] = None, description: Option[String] = None, created: Option[Instant] = None) 
	extends StorableWithFactory[FavorEvent]
{
	// IMPLEMENTED	--------------------
	
	override def factory = FavorEventModel.factory
	
	override def valueProperties = 
	{
		import FavorEventModel._
		Vector("id" -> id, relationshipIdAttName -> relationshipId, titleAttName -> title, 
			descriptionAttName -> description, createdAttName -> created)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param description A new description
	  * @return A new copy of this model with the specified description
	  */
	def withDescription(description: String) = copy(description = Some(description))
	
	/**
	  * @param relationshipId A new relationshipId
	  * @return A new copy of this model with the specified relationshipId
	  */
	def withRelationshipId(relationshipId: Int) = copy(relationshipId = Some(relationshipId))
	
	/**
	  * @param title A new title
	  * @return A new copy of this model with the specified title
	  */
	def withTitle(title: String) = copy(title = Some(title))
}


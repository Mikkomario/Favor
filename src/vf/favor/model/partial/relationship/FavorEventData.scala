package vf.favor.model.partial.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Represents a recognized event where a user performs a favor to another user
  * @param relationshipId Id of the relationship in which this even occurred
  * @param title Title of this favor event
  * @param description More detailed description of this favor event
  * @param created Time when this favor was recognized
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorEventData(relationshipId: Int, title: String, description: String, 
	created: Instant = Instant.now()) 
	extends ModelConvertible
{
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("relationship_id" -> relationshipId, "title" -> title, "description" -> description, 
			"created" -> created))
}


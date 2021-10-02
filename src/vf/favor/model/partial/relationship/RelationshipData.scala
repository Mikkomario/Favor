package vf.favor.model.partial.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Represents an established (directional) relationship between two users.
  * @param userId Id of the primary user
  * @param targetUserId Id of the user the primary user has a relationship with
  * @param currentFavor The primary user's favor towards the target user (cumulative)
  * @param created Time when this relationship was started
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RelationshipData(userId: Int, targetUserId: Int, currentFavor: Int, 
	created: Instant = Instant.now()) 
	extends ModelConvertible
{
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("user_id" -> userId, "target_user_id" -> targetUserId, "current_favor" -> currentFavor, 
			"created" -> created))
}


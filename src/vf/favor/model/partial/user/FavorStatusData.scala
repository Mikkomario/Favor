package vf.favor.model.partial.user

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Saves an user's cumulative favor status summaries (with history)
  * @param userId Id of the user linked with this FavorStatus
  * @param favorGiven Total amount of favor given by this user throughout history
  * @param favorReceived Total amount of favor received by this user from any other user throughout history
  * @param favorCirculated The amount of given favor received back throughout history
  * @param created Time when this status was registered
  * @param deprecatedAfter Time when this status was replaced with a newer version
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorStatusData(userId: Int, favorGiven: Int, favorReceived: Int, favorCirculated: Int, 
	created: Instant = Instant.now(), deprecatedAfter: Option[Instant] = None) 
	extends ModelConvertible
{
	// COMPUTED	--------------------
	
	/**
	  * Whether this FavorStatus has already been deprecated
	  */
	def isDeprecated = deprecatedAfter.isDefined
	
	/**
	  * Whether this FavorStatus is still valid (not deprecated)
	  */
	def isValid = !isDeprecated
	
	
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("user_id" -> userId, "favor_given" -> favorGiven, "favor_received" -> favorReceived, 
			"favor_circulated" -> favorCirculated, "created" -> created, 
			"deprecated_after" -> deprecatedAfter))
}


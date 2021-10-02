package vf.favor.model.partial.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._
import vf.favor.model.enumeration.TrustLevel

/**
  * Represents a codename used for a user when trust level doesn't allow use of real identities
  * @param userId Id of the user who uses this mask / alias
  * @param name Alias used as the mask
  * @param minContactTrust Level of trust required for the intermediary contact user in order for this mask to be applicable
  * @param minIndirectTrust Level of trust required from the intermediary contact user towards the unknown user in order for this mask to be applicable
  * @param created Time when this Mask was first created
  * @param deprecatedAfter Time after which the owner decided to stop using this mask
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MaskData(userId: Int, name: String, minContactTrust: Option[TrustLevel], 
	minIndirectTrust: Option[TrustLevel], created: Instant = Instant.now(), 
	deprecatedAfter: Option[Instant] = None) 
	extends ModelConvertible
{
	// COMPUTED	--------------------
	
	/**
	  * Whether this Mask has already been deprecated
	  */
	def isDeprecated = deprecatedAfter.isDefined
	
	/**
	  * Whether this Mask is still valid (not deprecated)
	  */
	def isValid = !isDeprecated
	
	
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("user_id" -> userId, "name" -> name, 
			"min_contact_trust" -> minContactTrust.map { _.id }, 
			"min_indirect_trust" -> minIndirectTrust.map { _.id }, "created" -> created, 
			"deprecated_after" -> deprecatedAfter))
}


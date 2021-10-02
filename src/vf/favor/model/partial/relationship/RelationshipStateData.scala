package vf.favor.model.partial.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._
import vf.favor.model.enumeration.TrustLevel

/**
  * Used for storing the current favor and trust states in a relationship
  * @param relationshipId Id of the described relationship
  * @param trust The level of trust in the described relationship
  * @param favor The amount of cumulated favor in this relationship
  * @param created Time when this RelationshipState was first created
  * @param deprecatedAfter Time when this RelationshipState became deprecated. None while this RelationshipState is still valid.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RelationshipStateData(relationshipId: Int, trust: TrustLevel, favor: Int, 
	created: Instant = Instant.now(), deprecatedAfter: Option[Instant] = None) 
	extends ModelConvertible
{
	// COMPUTED	--------------------
	
	/**
	  * Whether this RelationshipState has already been deprecated
	  */
	def isDeprecated = deprecatedAfter.isDefined
	
	/**
	  * Whether this RelationshipState is still valid (not deprecated)
	  */
	def isValid = !isDeprecated
	
	
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("relationship_id" -> relationshipId, "trust" -> trust.id, "favor" -> favor, 
			"created" -> created, "deprecated_after" -> deprecatedAfter))
}


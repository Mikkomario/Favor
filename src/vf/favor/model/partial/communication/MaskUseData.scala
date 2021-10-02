package vf.favor.model.partial.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._

/**
  * Records which masks have been used towards which people
  * @param maskId Id of the mask linked with this MaskUse
  * @param viewerId Id of the user who sees this mask
  * @param created Time when this MaskUse was first created
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MaskUseData(maskId: Int, viewerId: Int, created: Instant = Instant.now()) extends ModelConvertible
{
	// IMPLEMENTED	--------------------
	
	override def toModel = Model(Vector("mask_id" -> maskId, "viewer_id" -> viewerId, "created" -> created))
}


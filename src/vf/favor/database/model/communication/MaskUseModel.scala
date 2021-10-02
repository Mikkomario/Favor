package vf.favor.database.model.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import vf.favor.database.factory.communication.MaskUseFactory
import vf.favor.model.partial.communication.MaskUseData
import vf.favor.model.stored.communication.MaskUse

/**
  * Used for constructing MaskUseModel instances and for inserting MaskUses to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MaskUseModel extends DataInserter[MaskUseModel, MaskUse, MaskUseData]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains MaskUse maskId
	  */
	val maskIdAttName = "maskId"
	
	/**
	  * Name of the property that contains MaskUse viewerId
	  */
	val viewerIdAttName = "viewerId"
	
	/**
	  * Name of the property that contains MaskUse created
	  */
	val createdAttName = "created"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains MaskUse maskId
	  */
	def maskIdColumn = table(maskIdAttName)
	
	/**
	  * Column that contains MaskUse viewerId
	  */
	def viewerIdColumn = table(viewerIdAttName)
	
	/**
	  * Column that contains MaskUse created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = MaskUseFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: MaskUseData) = 
		apply(None, Some(data.maskId), Some(data.viewerId), Some(data.created))
	
	override def complete(id: Value, data: MaskUseData) = MaskUse(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this MaskUse was first created
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param id A MaskUse id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param maskId Id of the mask linked with this MaskUse
	  * @return A model containing only the specified maskId
	  */
	def withMaskId(maskId: Int) = apply(maskId = Some(maskId))
	
	/**
	  * @param viewerId Id of the user who sees this mask
	  * @return A model containing only the specified viewerId
	  */
	def withViewerId(viewerId: Int) = apply(viewerId = Some(viewerId))
}

/**
  * Used for interacting with MaskUses in the database
  * @param id MaskUse database id
  * @param maskId Id of the mask linked with this MaskUse
  * @param viewerId Id of the user who sees this mask
  * @param created Time when this MaskUse was first created
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MaskUseModel(id: Option[Int] = None, maskId: Option[Int] = None, viewerId: Option[Int] = None, 
	created: Option[Instant] = None) 
	extends StorableWithFactory[MaskUse]
{
	// IMPLEMENTED	--------------------
	
	override def factory = MaskUseModel.factory
	
	override def valueProperties = 
	{
		import MaskUseModel._
		Vector("id" -> id, maskIdAttName -> maskId, viewerIdAttName -> viewerId, createdAttName -> created)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param maskId A new maskId
	  * @return A new copy of this model with the specified maskId
	  */
	def withMaskId(maskId: Int) = copy(maskId = Some(maskId))
	
	/**
	  * @param viewerId A new viewerId
	  * @return A new copy of this model with the specified viewerId
	  */
	def withViewerId(viewerId: Int) = copy(viewerId = Some(viewerId))
}


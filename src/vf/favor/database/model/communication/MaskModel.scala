package vf.favor.database.model.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import utopia.vault.nosql.storable.deprecation.DeprecatableAfter
import vf.favor.database.factory.communication.MaskFactory
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.partial.communication.MaskData
import vf.favor.model.stored.communication.Mask

/**
  * Used for constructing MaskModel instances and for inserting Masks to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MaskModel extends DataInserter[MaskModel, Mask, MaskData] with DeprecatableAfter[MaskModel]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains Mask userId
	  */
	val userIdAttName = "userId"
	
	/**
	  * Name of the property that contains Mask name
	  */
	val nameAttName = "name"
	
	/**
	  * Name of the property that contains Mask minContactTrust
	  */
	val minContactTrustAttName = "minContactTrust"
	
	/**
	  * Name of the property that contains Mask minIndirectTrust
	  */
	val minIndirectTrustAttName = "minIndirectTrust"
	
	/**
	  * Name of the property that contains Mask created
	  */
	val createdAttName = "created"
	
	/**
	  * Name of the property that contains Mask deprecatedAfter
	  */
	val deprecatedAfterAttName = "deprecatedAfter"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains Mask userId
	  */
	def userIdColumn = table(userIdAttName)
	
	/**
	  * Column that contains Mask name
	  */
	def nameColumn = table(nameAttName)
	
	/**
	  * Column that contains Mask minContactTrust
	  */
	def minContactTrustColumn = table(minContactTrustAttName)
	
	/**
	  * Column that contains Mask minIndirectTrust
	  */
	def minIndirectTrustColumn = table(minIndirectTrustAttName)
	
	/**
	  * Column that contains Mask created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * Column that contains Mask deprecatedAfter
	  */
	def deprecatedAfterColumn = table(deprecatedAfterAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = MaskFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: MaskData) = 
		apply(None, Some(data.userId), Some(data.name), data.minContactTrust, data.minIndirectTrust, 
			Some(data.created), data.deprecatedAfter)
	
	override def complete(id: Value, data: MaskData) = Mask(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this Mask was first created
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param deprecatedAfter Time after which the owner decided to stop using this mask
	  * @return A model containing only the specified deprecatedAfter
	  */
	def withDeprecatedAfter(deprecatedAfter: Instant) = apply(deprecatedAfter = Some(deprecatedAfter))
	
	/**
	  * @param id A Mask id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param minContactTrust Level of trust required for the intermediary contact user in order for this mask to be applicable
	  * @return A model containing only the specified minContactTrust
	  */
	def withMinContactTrust(minContactTrust: TrustLevel) = apply(minContactTrust = Some(minContactTrust))
	
	/**
	  * @param minIndirectTrust Level of trust required from the intermediary contact user towards the unknown user in order for this mask to be applicable
	  * @return A model containing only the specified minIndirectTrust
	  */
	def withMinIndirectTrust(minIndirectTrust: TrustLevel) = apply(minIndirectTrust = Some(minIndirectTrust))
	
	/**
	  * @param name Alias used as the mask
	  * @return A model containing only the specified name
	  */
	def withName(name: String) = apply(name = Some(name))
	
	/**
	  * @param userId Id of the user who uses this mask / alias
	  * @return A model containing only the specified userId
	  */
	def withUserId(userId: Int) = apply(userId = Some(userId))
}

/**
  * Used for interacting with Masks in the database
  * @param id Mask database id
  * @param userId Id of the user who uses this mask / alias
  * @param name Alias used as the mask
  * @param minContactTrust Level of trust required for the intermediary contact user in order for this mask to be applicable
  * @param minIndirectTrust Level of trust required from the intermediary contact user towards the unknown user in order for this mask to be applicable
  * @param created Time when this Mask was first created
  * @param deprecatedAfter Time after which the owner decided to stop using this mask
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MaskModel(id: Option[Int] = None, userId: Option[Int] = None, name: Option[String] = None, 
	minContactTrust: Option[TrustLevel] = None, minIndirectTrust: Option[TrustLevel] = None, 
	created: Option[Instant] = None, deprecatedAfter: Option[Instant] = None) 
	extends StorableWithFactory[Mask]
{
	// IMPLEMENTED	--------------------
	
	override def factory = MaskModel.factory
	
	override def valueProperties = 
	{
		import MaskModel._
		Vector("id" -> id, userIdAttName -> userId, nameAttName -> name, 
			minContactTrustAttName -> minContactTrust.map { _.id }, 
			minIndirectTrustAttName -> minIndirectTrust.map { _.id }, createdAttName -> created, 
			deprecatedAfterAttName -> deprecatedAfter)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param deprecatedAfter A new deprecatedAfter
	  * @return A new copy of this model with the specified deprecatedAfter
	  */
	def withDeprecatedAfter(deprecatedAfter: Instant) = copy(deprecatedAfter = Some(deprecatedAfter))
	
	/**
	  * @param minContactTrust A new minContactTrust
	  * @return A new copy of this model with the specified minContactTrust
	  */
	def withMinContactTrust(minContactTrust: TrustLevel) = copy(minContactTrust = Some(minContactTrust))
	
	/**
	  * @param minIndirectTrust A new minIndirectTrust
	  * @return A new copy of this model with the specified minIndirectTrust
	  */
	def withMinIndirectTrust(minIndirectTrust: TrustLevel) = copy(minIndirectTrust = Some(minIndirectTrust))
	
	/**
	  * @param name A new name
	  * @return A new copy of this model with the specified name
	  */
	def withName(name: String) = copy(name = Some(name))
	
	/**
	  * @param userId A new userId
	  * @return A new copy of this model with the specified userId
	  */
	def withUserId(userId: Int) = copy(userId = Some(userId))
}


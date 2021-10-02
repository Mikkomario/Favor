package vf.favor.database.access.single.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MaskFactory
import vf.favor.database.model.communication.MaskModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.stored.communication.Mask

/**
  * A common trait for access points that return individual and distinct Masks.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueMaskAccess 
	extends SingleRowModelAccess[Mask] with DistinctModelAccess[Mask, Option[Mask], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the user who uses this mask / alias. None if no instance (or value) was found.
	  */
	def userId(implicit connection: Connection) = pullColumn(model.userIdColumn).int
	
	/**
	  * Alias used as the mask. None if no instance (or value) was found.
	  */
	def name(implicit connection: Connection) = pullColumn(model.nameColumn).string
	
	/**
	  * Level of trust required for the intermediary contact user in order for this mask to be applicable. None if no instance (or value) was found.
	  */
	def minContactTrust(implicit connection: Connection) = 
		pullColumn(model.minContactTrustColumn).int.flatMap(TrustLevel.findForId)
	
	/**
	  * Level of trust required from the intermediary contact user towards the unknown user in order for this mask to be applicable. None if no instance (or value) was found.
	  */
	def minIndirectTrust(implicit connection: Connection) = 
		pullColumn(model.minIndirectTrustColumn).int.flatMap(TrustLevel.findForId)
	
	/**
	  * Time when this Mask was first created. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	/**
	  * Time after which the owner decided to stop using this mask. None if no instance (or value) was found.
	  */
	def deprecatedAfter(implicit connection: Connection) = pullColumn(model.deprecatedAfterColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted Mask instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any Mask instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the deprecatedAfter of the targeted Mask instance(s)
	  * @param newDeprecatedAfter A new deprecatedAfter to assign
	  * @return Whether any Mask instance was affected
	  */
	def deprecatedAfter_=(newDeprecatedAfter: Instant)(implicit connection: Connection) = 
		putColumn(model.deprecatedAfterColumn, newDeprecatedAfter)
	
	/**
	  * Updates the minContactTrust of the targeted Mask instance(s)
	  * @param newMinContactTrust A new minContactTrust to assign
	  * @return Whether any Mask instance was affected
	  */
	def minContactTrust_=(newMinContactTrust: TrustLevel)(implicit connection: Connection) = 
		putColumn(model.minContactTrustColumn, newMinContactTrust.id)
	
	/**
	  * Updates the minIndirectTrust of the targeted Mask instance(s)
	  * @param newMinIndirectTrust A new minIndirectTrust to assign
	  * @return Whether any Mask instance was affected
	  */
	def minIndirectTrust_=(newMinIndirectTrust: TrustLevel)(implicit connection: Connection) = 
		putColumn(model.minIndirectTrustColumn, newMinIndirectTrust.id)
	
	/**
	  * Updates the name of the targeted Mask instance(s)
	  * @param newName A new name to assign
	  * @return Whether any Mask instance was affected
	  */
	def name_=(newName: String)(implicit connection: Connection) = putColumn(model.nameColumn, newName)
	
	/**
	  * Updates the userId of the targeted Mask instance(s)
	  * @param newUserId A new userId to assign
	  * @return Whether any Mask instance was affected
	  */
	def userId_=(newUserId: Int)(implicit connection: Connection) = putColumn(model.userIdColumn, newUserId)
}


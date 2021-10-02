package vf.favor.database.access.many.communication

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MaskFactory
import vf.favor.database.model.communication.MaskModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.stored.communication.Mask

/**
  * A common trait for access points which target multiple Masks at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyMasksAccess extends ManyRowModelAccess[Mask] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * userIds of the accessible Masks
	  */
	def userIds(implicit connection: Connection) = pullColumn(model.userIdColumn)
		.flatMap { value => value.int }
	
	/**
	  * names of the accessible Masks
	  */
	def names(implicit connection: Connection) = pullColumn(model.nameColumn)
		.flatMap { value => value.string }
	
	/**
	  * minContactTrustLevels of the accessible Masks
	  */
	def minContactTrustLevels(implicit connection: Connection) = 
		pullColumn(model.minContactTrustColumn).flatMap { value => value.int.flatMap(TrustLevel.findForId) }
	
	/**
	  * minIndirectTrustLevels of the accessible Masks
	  */
	def minIndirectTrustLevels(implicit connection: Connection) = 
		pullColumn(model.minIndirectTrustColumn).flatMap { value => value.int.flatMap(TrustLevel.findForId) }
	
	/**
	  * createds of the accessible Masks
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	/**
	  * deprecatedAfters of the accessible Masks
	  */
	def deprecatedAfters(implicit connection: Connection) = 
		pullColumn(model.deprecatedAfterColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


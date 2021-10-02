package vf.favor.database.access.single.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MaskUseFactory
import vf.favor.database.model.communication.MaskUseModel
import vf.favor.model.stored.communication.MaskUse

/**
  * A common trait for access points that return individual and distinct MaskUses.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueMaskUseAccess 
	extends SingleRowModelAccess[MaskUse] with DistinctModelAccess[MaskUse, Option[MaskUse], Value] 
		with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the mask linked with this MaskUse. None if no instance (or value) was found.
	  */
	def maskId(implicit connection: Connection) = pullColumn(model.maskIdColumn).int
	
	/**
	  * Id of the user who sees this mask. None if no instance (or value) was found.
	  */
	def viewerId(implicit connection: Connection) = pullColumn(model.viewerIdColumn).int
	
	/**
	  * Time when this MaskUse was first created. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskUseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskUseFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted MaskUse instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any MaskUse instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the maskId of the targeted MaskUse instance(s)
	  * @param newMaskId A new maskId to assign
	  * @return Whether any MaskUse instance was affected
	  */
	def maskId_=(newMaskId: Int)(implicit connection: Connection) = putColumn(model.maskIdColumn, newMaskId)
	
	/**
	  * Updates the viewerId of the targeted MaskUse instance(s)
	  * @param newViewerId A new viewerId to assign
	  * @return Whether any MaskUse instance was affected
	  */
	def viewerId_=(newViewerId: Int)(implicit connection: Connection) = 
		putColumn(model.viewerIdColumn, newViewerId)
}


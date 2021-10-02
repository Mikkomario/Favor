package vf.favor.database.access.many.communication

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MaskUseFactory
import vf.favor.database.model.communication.MaskUseModel
import vf.favor.model.stored.communication.MaskUse

/**
  * A common trait for access points which target multiple MaskUses at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyMaskUsesAccess extends ManyRowModelAccess[MaskUse] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * maskIds of the accessible MaskUses
	  */
	def maskIds(implicit connection: Connection) = pullColumn(model.maskIdColumn)
		.flatMap { value => value.int }
	
	/**
	  * viewerIds of the accessible MaskUses
	  */
	def viewerIds(implicit connection: Connection) = 
		pullColumn(model.viewerIdColumn).flatMap { value => value.int }
	
	/**
	  * createds of the accessible MaskUses
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskUseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskUseFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


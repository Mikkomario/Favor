package vf.favor.database.access.many.relationship

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.RelationshipStateFactory
import vf.favor.database.model.relationship.RelationshipStateModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.stored.relationship.RelationshipState

/**
  * A common trait for access points which target multiple RelationshipStates at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyRelationshipStatesAccess extends ManyRowModelAccess[RelationshipState] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * relationshipIds of the accessible RelationshipStates
	  */
	def relationshipIds(implicit connection: Connection) = 
		pullColumn(model.relationshipIdColumn).flatMap { value => value.int }
	
	/**
	  * trustLevels of the accessible RelationshipStates
	  */
	def trustLevels(implicit connection: Connection) = 
		pullColumn(model.trustColumn).flatMap { value => value.int.flatMap(TrustLevel.findForId) }
	
	/**
	  * favor of the accessible RelationshipStates
	  */
	def favor(implicit connection: Connection) = pullColumn(model.favorColumn).flatMap { value => value.int }
	
	/**
	  * createds of the accessible RelationshipStates
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	/**
	  * deprecatedAfters of the accessible RelationshipStates
	  */
	def deprecatedAfters(implicit connection: Connection) = 
		pullColumn(model.deprecatedAfterColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipStateModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipStateFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted RelationshipState instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any RelationshipState instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the deprecatedAfter of the targeted RelationshipState instance(s)
	  * @param newDeprecatedAfter A new deprecatedAfter to assign
	  * @return Whether any RelationshipState instance was affected
	  */
	def deprecatedAfter_=(newDeprecatedAfter: Instant)(implicit connection: Connection) = 
		putColumn(model.deprecatedAfterColumn, newDeprecatedAfter)
	
	/**
	  * Updates the favor of the targeted RelationshipState instance(s)
	  * @param newFavor A new favor to assign
	  * @return Whether any RelationshipState instance was affected
	  */
	def favor_=(newFavor: Int)(implicit connection: Connection) = putColumn(model.favorColumn, newFavor)
	
	/**
	  * Updates the relationshipId of the targeted RelationshipState instance(s)
	  * @param newRelationshipId A new relationshipId to assign
	  * @return Whether any RelationshipState instance was affected
	  */
	def relationshipId_=(newRelationshipId: Int)(implicit connection: Connection) = 
		putColumn(model.relationshipIdColumn, newRelationshipId)
	
	/**
	  * Updates the trust of the targeted RelationshipState instance(s)
	  * @param newTrust A new trust to assign
	  * @return Whether any RelationshipState instance was affected
	  */
	def trust_=(newTrust: TrustLevel)(implicit connection: Connection) = putColumn(model.trustColumn, 
		newTrust.id)
}


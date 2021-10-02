package vf.favor.database.access.single.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.relationship.RelationshipStateFactory
import vf.favor.database.model.relationship.RelationshipStateModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.stored.relationship.RelationshipState

/**
  * A common trait for access points that return individual and distinct RelationshipStates.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueRelationshipStateAccess 
	extends SingleRowModelAccess[RelationshipState] 
		with DistinctModelAccess[RelationshipState, Option[RelationshipState], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the described relationship. None if no instance (or value) was found.
	  */
	def relationshipId(implicit connection: Connection) = pullColumn(model.relationshipIdColumn).int
	
	/**
	  * The level of trust in the described relationship. None if no instance (or value) was found.
	  */
	def trust(implicit connection: Connection) = pullColumn(model.trustColumn).int.flatMap(TrustLevel.findForId)
	
	/**
	  * The amount of cumulated favor in this relationship. None if no instance (or value) was found.
	  */
	def favor(implicit connection: Connection) = pullColumn(model.favorColumn).int
	
	/**
	  * Time when this RelationshipState was first created. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	/**
	  * Time when this RelationshipState became deprecated. None while this RelationshipState is still valid.. None if no instance (or value) was found.
	  */
	def deprecatedAfter(implicit connection: Connection) = pullColumn(model.deprecatedAfterColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipStateModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipStateFactory
	
	
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


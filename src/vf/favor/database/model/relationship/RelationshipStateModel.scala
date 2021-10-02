package vf.favor.database.model.relationship

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import utopia.vault.nosql.storable.deprecation.DeprecatableAfter
import vf.favor.database.factory.relationship.RelationshipStateFactory
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.partial.relationship.RelationshipStateData
import vf.favor.model.stored.relationship.RelationshipState

/**
  * Used for constructing RelationshipStateModel instances and for inserting RelationshipStates to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RelationshipStateModel 
	extends DataInserter[RelationshipStateModel, RelationshipState, RelationshipStateData] 
		with DeprecatableAfter[RelationshipStateModel]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains RelationshipState relationshipId
	  */
	val relationshipIdAttName = "relationshipId"
	
	/**
	  * Name of the property that contains RelationshipState trust
	  */
	val trustAttName = "trust"
	
	/**
	  * Name of the property that contains RelationshipState favor
	  */
	val favorAttName = "favor"
	
	/**
	  * Name of the property that contains RelationshipState created
	  */
	val createdAttName = "created"
	
	/**
	  * Name of the property that contains RelationshipState deprecatedAfter
	  */
	val deprecatedAfterAttName = "deprecatedAfter"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains RelationshipState relationshipId
	  */
	def relationshipIdColumn = table(relationshipIdAttName)
	
	/**
	  * Column that contains RelationshipState trust
	  */
	def trustColumn = table(trustAttName)
	
	/**
	  * Column that contains RelationshipState favor
	  */
	def favorColumn = table(favorAttName)
	
	/**
	  * Column that contains RelationshipState created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * Column that contains RelationshipState deprecatedAfter
	  */
	def deprecatedAfterColumn = table(deprecatedAfterAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = RelationshipStateFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: RelationshipStateData) = 
		apply(None, Some(data.relationshipId), Some(data.trust), Some(data.favor), Some(data.created), 
			data.deprecatedAfter)
	
	override def complete(id: Value, data: RelationshipStateData) = RelationshipState(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this RelationshipState was first created
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param deprecatedAfter Time when this RelationshipState became deprecated. None while this RelationshipState is still valid.
	  * @return A model containing only the specified deprecatedAfter
	  */
	def withDeprecatedAfter(deprecatedAfter: Instant) = apply(deprecatedAfter = Some(deprecatedAfter))
	
	/**
	  * @param favor The amount of cumulated favor in this relationship
	  * @return A model containing only the specified favor
	  */
	def withFavor(favor: Int) = apply(favor = Some(favor))
	
	/**
	  * @param id A RelationshipState id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param relationshipId Id of the described relationship
	  * @return A model containing only the specified relationshipId
	  */
	def withRelationshipId(relationshipId: Int) = apply(relationshipId = Some(relationshipId))
	
	/**
	  * @param trust The level of trust in the described relationship
	  * @return A model containing only the specified trust
	  */
	def withTrust(trust: TrustLevel) = apply(trust = Some(trust))
}

/**
  * Used for interacting with RelationshipStates in the database
  * @param id RelationshipState database id
  * @param relationshipId Id of the described relationship
  * @param trust The level of trust in the described relationship
  * @param favor The amount of cumulated favor in this relationship
  * @param created Time when this RelationshipState was first created
  * @param deprecatedAfter Time when this RelationshipState became deprecated. None while this RelationshipState is still valid.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RelationshipStateModel(id: Option[Int] = None, relationshipId: Option[Int] = None, 
	trust: Option[TrustLevel] = None, favor: Option[Int] = None, created: Option[Instant] = None, 
	deprecatedAfter: Option[Instant] = None) 
	extends StorableWithFactory[RelationshipState]
{
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipStateModel.factory
	
	override def valueProperties = 
	{
		import RelationshipStateModel._
		Vector("id" -> id, relationshipIdAttName -> relationshipId, trustAttName -> trust.map { _.id }, 
			favorAttName -> favor, createdAttName -> created, deprecatedAfterAttName -> deprecatedAfter)
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
	  * @param favor A new favor
	  * @return A new copy of this model with the specified favor
	  */
	def withFavor(favor: Int) = copy(favor = Some(favor))
	
	/**
	  * @param relationshipId A new relationshipId
	  * @return A new copy of this model with the specified relationshipId
	  */
	def withRelationshipId(relationshipId: Int) = copy(relationshipId = Some(relationshipId))
	
	/**
	  * @param trust A new trust
	  * @return A new copy of this model with the specified trust
	  */
	def withTrust(trust: TrustLevel) = copy(trust = Some(trust))
}


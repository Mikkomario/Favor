package vf.favor.database.access.single.relationship

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.UnconditionalView
import vf.favor.database.factory.relationship.RelationshipFactory
import vf.favor.database.model.relationship.RelationshipModel
import vf.favor.model.stored.relationship.Relationship

/**
  * Used for accessing individual Relationships
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRelationship extends SingleRowModelAccess[Relationship] with UnconditionalView with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted Relationship instance
	  * @return An access point to that Relationship
	  */
	def apply(id: Int) = new DbSingleRelationship(id)
	
	
	// NESTED	--------------------
	
	class DbSingleRelationship(val id: Int) extends UniqueRelationshipAccess 
		with UniqueModelAccess[Relationship]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


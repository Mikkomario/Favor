package vf.favor.database.access.single.relationship

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.database.factory.relationship.RelationshipStateFactory
import vf.favor.database.model.relationship.RelationshipStateModel
import vf.favor.model.stored.relationship.RelationshipState

/**
  * Used for accessing individual RelationshipStates
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRelationshipState 
	extends SingleRowModelAccess[RelationshipState] with NonDeprecatedView[RelationshipState] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RelationshipStateModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RelationshipStateFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted RelationshipState instance
	  * @return An access point to that RelationshipState
	  */
	def apply(id: Int) = new DbSingleRelationshipState(id)
	
	
	// NESTED	--------------------
	
	class DbSingleRelationshipState(val id: Int) 
		extends UniqueRelationshipStateAccess with UniqueModelAccess[RelationshipState]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


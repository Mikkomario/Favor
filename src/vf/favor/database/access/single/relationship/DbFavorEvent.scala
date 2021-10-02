package vf.favor.database.access.single.relationship

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.UnconditionalView
import vf.favor.database.factory.relationship.FavorEventFactory
import vf.favor.database.model.relationship.FavorEventModel
import vf.favor.model.stored.relationship.FavorEvent

/**
  * Used for accessing individual FavorEvents
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbFavorEvent extends SingleRowModelAccess[FavorEvent] with UnconditionalView with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorEventModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorEventFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted FavorEvent instance
	  * @return An access point to that FavorEvent
	  */
	def apply(id: Int) = new DbSingleFavorEvent(id)
	
	
	// NESTED	--------------------
	
	class DbSingleFavorEvent(val id: Int) extends UniqueFavorEventAccess with UniqueModelAccess[FavorEvent]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


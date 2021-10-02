package vf.favor.database.access.single.user

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.database.factory.user.FavorStatusFactory
import vf.favor.database.model.user.FavorStatusModel
import vf.favor.model.stored.user.FavorStatus

/**
  * Used for accessing individual FavorStatuss
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbFavorStatus 
	extends SingleRowModelAccess[FavorStatus] with NonDeprecatedView[FavorStatus] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorStatusModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorStatusFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted FavorStatus instance
	  * @return An access point to that FavorStatus
	  */
	def apply(id: Int) = new DbSingleFavorStatus(id)
	
	
	// NESTED	--------------------
	
	class DbSingleFavorStatus(val id: Int) extends UniqueFavorStatusAccess with UniqueModelAccess[FavorStatus]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


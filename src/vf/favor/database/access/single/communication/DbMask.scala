package vf.favor.database.access.single.communication

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.database.factory.communication.MaskFactory
import vf.favor.database.model.communication.MaskModel
import vf.favor.model.stored.communication.Mask

/**
  * Used for accessing individual Masks
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMask extends SingleRowModelAccess[Mask] with NonDeprecatedView[Mask] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted Mask instance
	  * @return An access point to that Mask
	  */
	def apply(id: Int) = new DbSingleMask(id)
	
	
	// NESTED	--------------------
	
	class DbSingleMask(val id: Int) extends UniqueMaskAccess with UniqueModelAccess[Mask]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


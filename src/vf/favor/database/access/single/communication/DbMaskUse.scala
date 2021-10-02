package vf.favor.database.access.single.communication

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.UnconditionalView
import vf.favor.database.factory.communication.MaskUseFactory
import vf.favor.database.model.communication.MaskUseModel
import vf.favor.model.stored.communication.MaskUse

/**
  * Used for accessing individual MaskUses
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMaskUse extends SingleRowModelAccess[MaskUse] with UnconditionalView with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MaskUseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MaskUseFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted MaskUse instance
	  * @return An access point to that MaskUse
	  */
	def apply(id: Int) = new DbSingleMaskUse(id)
	
	
	// NESTED	--------------------
	
	class DbSingleMaskUse(val id: Int) extends UniqueMaskUseAccess with UniqueModelAccess[MaskUse]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


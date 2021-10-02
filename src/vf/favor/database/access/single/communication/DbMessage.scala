package vf.favor.database.access.single.communication

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.database.factory.communication.MessageFactory
import vf.favor.database.model.communication.MessageModel
import vf.favor.model.stored.communication.Message

/**
  * Used for accessing individual Messages
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMessage extends SingleRowModelAccess[Message] with NonDeprecatedView[Message] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MessageModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MessageFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted Message instance
	  * @return An access point to that Message
	  */
	def apply(id: Int) = new DbSingleMessage(id)
	
	
	// NESTED	--------------------
	
	class DbSingleMessage(val id: Int) extends UniqueMessageAccess with UniqueModelAccess[Message]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


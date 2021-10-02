package vf.favor.database.factory.communication

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import utopia.vault.nosql.template.Deprecatable
import vf.favor.database.FavorTables
import vf.favor.database.model.communication.MessageModel
import vf.favor.model.partial.communication.MessageData
import vf.favor.model.stored.communication.Message

/**
  * Used for reading Message data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MessageFactory 
	extends FromValidatedRowModelFactory[Message] with FromRowFactoryWithTimestamps[Message] with Deprecatable
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def nonDeprecatedCondition = MessageModel.nonDeprecatedCondition
	
	override def table = FavorTables.message
	
	override def fromValidatedModel(model: Model[Constant]) = 
		Message(model("id"), MessageData(model("senderId"), model("recipientId"), model("text"), 
			model("expires"), model("created")))
}


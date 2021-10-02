package vf.favor.database.factory.relationship

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import vf.favor.database.FavorTables
import vf.favor.model.partial.relationship.FavorEventData
import vf.favor.model.stored.relationship.FavorEvent

/**
  * Used for reading FavorEvent data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object FavorEventFactory 
	extends FromValidatedRowModelFactory[FavorEvent] with FromRowFactoryWithTimestamps[FavorEvent]
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def table = FavorTables.favorEvent
	
	override def fromValidatedModel(model: Model[Constant]) = 
		FavorEvent(model("id"), FavorEventData(model("relationshipId"), model("title"), model("description"), 
			model("created")))
}


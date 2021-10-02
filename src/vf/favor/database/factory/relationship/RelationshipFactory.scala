package vf.favor.database.factory.relationship

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import vf.favor.database.FavorTables
import vf.favor.model.partial.relationship.RelationshipData
import vf.favor.model.stored.relationship.Relationship

/**
  * Used for reading Relationship data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RelationshipFactory 
	extends FromValidatedRowModelFactory[Relationship] with FromRowFactoryWithTimestamps[Relationship]
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def table = FavorTables.relationship
	
	override def fromValidatedModel(model: Model[Constant]) = 
		Relationship(model("id"), RelationshipData(model("userId"), model("targetUserId"), 
			model("currentFavor"), model("created")))
}


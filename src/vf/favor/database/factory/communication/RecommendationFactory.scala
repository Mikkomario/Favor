package vf.favor.database.factory.communication

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import vf.favor.database.FavorTables
import vf.favor.model.partial.communication.RecommendationData
import vf.favor.model.stored.communication.Recommendation

/**
  * Used for reading Recommendation data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RecommendationFactory 
	extends FromValidatedRowModelFactory[Recommendation] with FromRowFactoryWithTimestamps[Recommendation]
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def table = FavorTables.recommendation
	
	override def fromValidatedModel(model: Model[Constant]) = 
		Recommendation(model("id"), RecommendationData(model("senderId"), model("recipientId"), 
			model("recommendedId"), model("message"), model("created")))
}


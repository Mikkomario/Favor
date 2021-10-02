package vf.favor.database.factory.communication

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import vf.favor.database.FavorTables
import vf.favor.model.partial.communication.RecommendationResponseData
import vf.favor.model.stored.communication.RecommendationResponse

/**
  * Used for reading RecommendationResponse data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RecommendationResponseFactory 
	extends FromValidatedRowModelFactory[RecommendationResponse] 
		with FromRowFactoryWithTimestamps[RecommendationResponse]
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def table = FavorTables.recommendationResponse
	
	override def fromValidatedModel(model: Model[Constant]) = 
		RecommendationResponse(model("id"), RecommendationResponseData(model("recommendationId"), 
			model("newTrustLevel"), model("message"), model("created")))
}


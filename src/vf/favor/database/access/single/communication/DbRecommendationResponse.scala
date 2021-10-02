package vf.favor.database.access.single.communication

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.UnconditionalView
import vf.favor.database.factory.communication.RecommendationResponseFactory
import vf.favor.database.model.communication.RecommendationResponseModel
import vf.favor.model.stored.communication.RecommendationResponse

/**
  * Used for accessing individual RecommendationResponses
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRecommendationResponse 
	extends SingleRowModelAccess[RecommendationResponse] with UnconditionalView with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationResponseModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationResponseFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted RecommendationResponse instance
	  * @return An access point to that RecommendationResponse
	  */
	def apply(id: Int) = new DbSingleRecommendationResponse(id)
	
	
	// NESTED	--------------------
	
	class DbSingleRecommendationResponse(val id: Int) 
		extends UniqueRecommendationResponseAccess with UniqueModelAccess[RecommendationResponse]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


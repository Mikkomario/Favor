package vf.favor.database.access.single.communication

import utopia.flow.generic.ValueConversions._
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.single.model.distinct.UniqueModelAccess
import utopia.vault.nosql.template.Indexed
import utopia.vault.nosql.view.UnconditionalView
import vf.favor.database.factory.communication.RecommendationFactory
import vf.favor.database.model.communication.RecommendationModel
import vf.favor.model.stored.communication.Recommendation

/**
  * Used for accessing individual Recommendations
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRecommendation extends SingleRowModelAccess[Recommendation] with UnconditionalView with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = RecommendationModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = RecommendationFactory
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Database id of the targeted Recommendation instance
	  * @return An access point to that Recommendation
	  */
	def apply(id: Int) = new DbSingleRecommendation(id)
	
	
	// NESTED	--------------------
	
	class DbSingleRecommendation(val id: Int) 
		extends UniqueRecommendationAccess with UniqueModelAccess[Recommendation]
	{
		// IMPLEMENTED	--------------------
		
		override def condition = index <=> id
	}
}


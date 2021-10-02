package vf.favor.model.stored.communication

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.communication.RecommendationData

/**
  * Represents a Recommendation that has already been stored in the database
  * @param id id of this Recommendation in the database
  * @param data Wrapped Recommendation data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class Recommendation(id: Int, data: RecommendationData) 
	extends StoredModelConvertible[RecommendationData]


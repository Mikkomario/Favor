package vf.favor.model.stored.communication

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.communication.RecommendationResponseData

/**
  * Represents a RecommendationResponse that has already been stored in the database
  * @param id id of this RecommendationResponse in the database
  * @param data Wrapped RecommendationResponse data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RecommendationResponse(id: Int, data: RecommendationResponseData) 
	extends StoredModelConvertible[RecommendationResponseData]


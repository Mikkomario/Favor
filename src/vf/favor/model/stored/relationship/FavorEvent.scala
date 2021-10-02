package vf.favor.model.stored.relationship

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.relationship.FavorEventData

/**
  * Represents a FavorEvent that has already been stored in the database
  * @param id id of this FavorEvent in the database
  * @param data Wrapped FavorEvent data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorEvent(id: Int, data: FavorEventData) extends StoredModelConvertible[FavorEventData]


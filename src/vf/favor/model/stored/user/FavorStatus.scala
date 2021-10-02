package vf.favor.model.stored.user

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.user.FavorStatusData

/**
  * Represents a FavorStatus that has already been stored in the database
  * @param id id of this FavorStatus in the database
  * @param data Wrapped FavorStatus data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorStatus(id: Int, data: FavorStatusData) extends StoredModelConvertible[FavorStatusData]


package vf.favor.model.stored.communication

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.communication.MaskData

/**
  * Represents a Mask that has already been stored in the database
  * @param id id of this Mask in the database
  * @param data Wrapped Mask data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class Mask(id: Int, data: MaskData) extends StoredModelConvertible[MaskData]


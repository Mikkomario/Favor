package vf.favor.model.stored.communication

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.communication.MaskUseData

/**
  * Represents a MaskUse that has already been stored in the database
  * @param id id of this MaskUse in the database
  * @param data Wrapped MaskUse data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MaskUse(id: Int, data: MaskUseData) extends StoredModelConvertible[MaskUseData]


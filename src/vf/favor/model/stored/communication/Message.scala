package vf.favor.model.stored.communication

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.communication.MessageData

/**
  * Represents a Message that has already been stored in the database
  * @param id id of this Message in the database
  * @param data Wrapped Message data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class Message(id: Int, data: MessageData) extends StoredModelConvertible[MessageData]


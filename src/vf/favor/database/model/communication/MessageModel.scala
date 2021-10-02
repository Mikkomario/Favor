package vf.favor.database.model.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import utopia.vault.nosql.storable.deprecation.Expiring
import vf.favor.database.factory.communication.MessageFactory
import vf.favor.model.partial.communication.MessageData
import vf.favor.model.stored.communication.Message

/**
  * Used for constructing MessageModel instances and for inserting Messages to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MessageModel extends DataInserter[MessageModel, Message, MessageData] with Expiring
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains Message senderId
	  */
	val senderIdAttName = "senderId"
	
	/**
	  * Name of the property that contains Message recipientId
	  */
	val recipientIdAttName = "recipientId"
	
	/**
	  * Name of the property that contains Message text
	  */
	val textAttName = "text"
	
	/**
	  * Name of the property that contains Message expires
	  */
	val expiresAttName = "expires"
	
	/**
	  * Name of the property that contains Message created
	  */
	val createdAttName = "created"
	
	override val deprecationAttName = "expires"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains Message senderId
	  */
	def senderIdColumn = table(senderIdAttName)
	
	/**
	  * Column that contains Message recipientId
	  */
	def recipientIdColumn = table(recipientIdAttName)
	
	/**
	  * Column that contains Message text
	  */
	def textColumn = table(textAttName)
	
	/**
	  * Column that contains Message expires
	  */
	def expiresColumn = table(expiresAttName)
	
	/**
	  * Column that contains Message created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = MessageFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: MessageData) = 
		apply(None, Some(data.senderId), Some(data.recipientId), Some(data.text), Some(data.expires), 
			Some(data.created))
	
	override def complete(id: Value, data: MessageData) = Message(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this message was sent
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param expires Time when this message expires / should be deleted
	  * @return A model containing only the specified expires
	  */
	def withExpires(expires: Instant) = apply(expires = Some(expires))
	
	/**
	  * @param id A Message id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param recipientId Id of the user who received this message
	  * @return A model containing only the specified recipientId
	  */
	def withRecipientId(recipientId: Int) = apply(recipientId = Some(recipientId))
	
	/**
	  * @param senderId Id of the user who sent this message
	  * @return A model containing only the specified senderId
	  */
	def withSenderId(senderId: Int) = apply(senderId = Some(senderId))
	
	/**
	  * @param text Message content
	  * @return A model containing only the specified text
	  */
	def withText(text: String) = apply(text = Some(text))
}

/**
  * Used for interacting with Messages in the database
  * @param id Message database id
  * @param senderId Id of the user who sent this message
  * @param recipientId Id of the user who received this message
  * @param text Message content
  * @param expires Time when this message expires / should be deleted
  * @param created Time when this message was sent
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MessageModel(id: Option[Int] = None, senderId: Option[Int] = None, 
	recipientId: Option[Int] = None, text: Option[String] = None, expires: Option[Instant] = None, 
	created: Option[Instant] = None) 
	extends StorableWithFactory[Message]
{
	// IMPLEMENTED	--------------------
	
	override def factory = MessageModel.factory
	
	override def valueProperties = 
	{
		import MessageModel._
		Vector("id" -> id, senderIdAttName -> senderId, recipientIdAttName -> recipientId, 
			textAttName -> text, expiresAttName -> expires, createdAttName -> created)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param expires A new expires
	  * @return A new copy of this model with the specified expires
	  */
	def withExpires(expires: Instant) = copy(expires = Some(expires))
	
	/**
	  * @param recipientId A new recipientId
	  * @return A new copy of this model with the specified recipientId
	  */
	def withRecipientId(recipientId: Int) = copy(recipientId = Some(recipientId))
	
	/**
	  * @param senderId A new senderId
	  * @return A new copy of this model with the specified senderId
	  */
	def withSenderId(senderId: Int) = copy(senderId = Some(senderId))
	
	/**
	  * @param text A new text
	  * @return A new copy of this model with the specified text
	  */
	def withText(text: String) = copy(text = Some(text))
}


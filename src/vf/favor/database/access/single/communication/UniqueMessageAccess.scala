package vf.favor.database.access.single.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MessageFactory
import vf.favor.database.model.communication.MessageModel
import vf.favor.model.stored.communication.Message

/**
  * A common trait for access points that return individual and distinct Messages.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueMessageAccess 
	extends SingleRowModelAccess[Message] with DistinctModelAccess[Message, Option[Message], Value] 
		with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the user who sent this message. None if no instance (or value) was found.
	  */
	def senderId(implicit connection: Connection) = pullColumn(model.senderIdColumn).int
	
	/**
	  * Id of the user who received this message. None if no instance (or value) was found.
	  */
	def recipientId(implicit connection: Connection) = pullColumn(model.recipientIdColumn).int
	
	/**
	  * Message content. None if no instance (or value) was found.
	  */
	def text(implicit connection: Connection) = pullColumn(model.textColumn).string
	
	/**
	  * Time when this message expires / should be deleted. None if no instance (or value) was found.
	  */
	def expires(implicit connection: Connection) = pullColumn(model.expiresColumn).instant
	
	/**
	  * Time when this message was sent. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MessageModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MessageFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted Message instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any Message instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the expires of the targeted Message instance(s)
	  * @param newExpires A new expires to assign
	  * @return Whether any Message instance was affected
	  */
	def expires_=(newExpires: Instant)(implicit connection: Connection) = 
		putColumn(model.expiresColumn, newExpires)
	
	/**
	  * Updates the recipientId of the targeted Message instance(s)
	  * @param newRecipientId A new recipientId to assign
	  * @return Whether any Message instance was affected
	  */
	def recipientId_=(newRecipientId: Int)(implicit connection: Connection) = 
		putColumn(model.recipientIdColumn, newRecipientId)
	
	/**
	  * Updates the senderId of the targeted Message instance(s)
	  * @param newSenderId A new senderId to assign
	  * @return Whether any Message instance was affected
	  */
	def senderId_=(newSenderId: Int)(implicit connection: Connection) = 
		putColumn(model.senderIdColumn, newSenderId)
	
	/**
	  * Updates the text of the targeted Message instance(s)
	  * @param newText A new text to assign
	  * @return Whether any Message instance was affected
	  */
	def text_=(newText: String)(implicit connection: Connection) = putColumn(model.textColumn, newText)
}


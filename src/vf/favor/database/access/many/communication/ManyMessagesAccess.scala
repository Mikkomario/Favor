package vf.favor.database.access.many.communication

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.communication.MessageFactory
import vf.favor.database.model.communication.MessageModel
import vf.favor.model.stored.communication.Message

/**
  * A common trait for access points which target multiple Messages at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyMessagesAccess extends ManyRowModelAccess[Message] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * senderIds of the accessible Messages
	  */
	def senderIds(implicit connection: Connection) = 
		pullColumn(model.senderIdColumn).flatMap { value => value.int }
	
	/**
	  * recipientIds of the accessible Messages
	  */
	def recipientIds(implicit connection: Connection) = 
		pullColumn(model.recipientIdColumn).flatMap { value => value.int }
	
	/**
	  * contents of the accessible Messages
	  */
	def contents(implicit connection: Connection) = pullColumn(model.textColumn)
		.flatMap { value => value.string }
	
	/**
	  * expiress of the accessible Messages
	  */
	def expiress(implicit connection: Connection) = 
		pullColumn(model.expiresColumn).flatMap { value => value.instant }
	
	/**
	  * createds of the accessible Messages
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = MessageModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = MessageFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


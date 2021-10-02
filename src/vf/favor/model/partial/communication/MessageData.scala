package vf.favor.model.partial.communication

import java.time.Instant
import utopia.flow.datastructure.immutable.Model
import utopia.flow.generic.ModelConvertible
import utopia.flow.generic.ValueConversions._
import utopia.flow.time.Now
import utopia.flow.time.TimeExtensions._

/**
  * Represents a message from one user to another
  * @param senderId Id of the user who sent this message
  * @param recipientId Id of the user who received this message
  * @param text Message content
  * @param expires Time when this message expires / should be deleted
  * @param created Time when this message was sent
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class MessageData(senderId: Int, recipientId: Int, text: String, expires: Instant, 
	created: Instant = Instant.now()) 
	extends ModelConvertible
{
	// COMPUTED	--------------------
	
	/**
	  * Whether this Message is no longer valid because it has expired
	  */
	def hasExpired = expires <= Now
	
	/**
	  * Whether this Message is still valid (hasn't expired yet)
	  */
	def isValid = !hasExpired
	
	
	// IMPLEMENTED	--------------------
	
	override def toModel = 
		Model(Vector("sender_id" -> senderId, "recipient_id" -> recipientId, "text" -> text, 
			"expires" -> expires, "created" -> created))
}


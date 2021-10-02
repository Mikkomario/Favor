package vf.favor.database.model.user

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.model.immutable.StorableWithFactory
import utopia.vault.nosql.storable.DataInserter
import utopia.vault.nosql.storable.deprecation.DeprecatableAfter
import vf.favor.database.factory.user.FavorStatusFactory
import vf.favor.model.partial.user.FavorStatusData
import vf.favor.model.stored.user.FavorStatus

/**
  * Used for constructing FavorStatusModel instances and for inserting FavorStatuss to the database
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object FavorStatusModel 
	extends DataInserter[FavorStatusModel, FavorStatus, FavorStatusData] 
		with DeprecatableAfter[FavorStatusModel]
{
	// ATTRIBUTES	--------------------
	
	/**
	  * Name of the property that contains FavorStatus userId
	  */
	val userIdAttName = "userId"
	
	/**
	  * Name of the property that contains FavorStatus favorGiven
	  */
	val favorGivenAttName = "favorGiven"
	
	/**
	  * Name of the property that contains FavorStatus favorReceived
	  */
	val favorReceivedAttName = "favorReceived"
	
	/**
	  * Name of the property that contains FavorStatus favorCirculated
	  */
	val favorCirculatedAttName = "favorCirculated"
	
	/**
	  * Name of the property that contains FavorStatus created
	  */
	val createdAttName = "created"
	
	/**
	  * Name of the property that contains FavorStatus deprecatedAfter
	  */
	val deprecatedAfterAttName = "deprecatedAfter"
	
	
	// COMPUTED	--------------------
	
	/**
	  * Column that contains FavorStatus userId
	  */
	def userIdColumn = table(userIdAttName)
	
	/**
	  * Column that contains FavorStatus favorGiven
	  */
	def favorGivenColumn = table(favorGivenAttName)
	
	/**
	  * Column that contains FavorStatus favorReceived
	  */
	def favorReceivedColumn = table(favorReceivedAttName)
	
	/**
	  * Column that contains FavorStatus favorCirculated
	  */
	def favorCirculatedColumn = table(favorCirculatedAttName)
	
	/**
	  * Column that contains FavorStatus created
	  */
	def createdColumn = table(createdAttName)
	
	/**
	  * Column that contains FavorStatus deprecatedAfter
	  */
	def deprecatedAfterColumn = table(deprecatedAfterAttName)
	
	/**
	  * The factory object used by this model type
	  */
	def factory = FavorStatusFactory
	
	
	// IMPLEMENTED	--------------------
	
	override def table = factory.table
	
	override def apply(data: FavorStatusData) = 
		apply(None, Some(data.userId), Some(data.favorGiven), Some(data.favorReceived), 
			Some(data.favorCirculated), Some(data.created), data.deprecatedAfter)
	
	override def complete(id: Value, data: FavorStatusData) = FavorStatus(id.getInt, data)
	
	
	// OTHER	--------------------
	
	/**
	  * @param created Time when this status was registered
	  * @return A model containing only the specified created
	  */
	def withCreated(created: Instant) = apply(created = Some(created))
	
	/**
	  * @param deprecatedAfter Time when this status was replaced with a newer version
	  * @return A model containing only the specified deprecatedAfter
	  */
	def withDeprecatedAfter(deprecatedAfter: Instant) = apply(deprecatedAfter = Some(deprecatedAfter))
	
	/**
	  * @param favorCirculated The amount of given favor received back throughout history
	  * @return A model containing only the specified favorCirculated
	  */
	def withFavorCirculated(favorCirculated: Int) = apply(favorCirculated = Some(favorCirculated))
	
	/**
	  * @param favorGiven Total amount of favor given by this user throughout history
	  * @return A model containing only the specified favorGiven
	  */
	def withFavorGiven(favorGiven: Int) = apply(favorGiven = Some(favorGiven))
	
	/**
	  * @param favorReceived Total amount of favor received by this user from any other user throughout history
	  * @return A model containing only the specified favorReceived
	  */
	def withFavorReceived(favorReceived: Int) = apply(favorReceived = Some(favorReceived))
	
	/**
	  * @param id A FavorStatus id
	  * @return A model with that id
	  */
	def withId(id: Int) = apply(Some(id))
	
	/**
	  * @param userId Id of the user linked with this FavorStatus
	  * @return A model containing only the specified userId
	  */
	def withUserId(userId: Int) = apply(userId = Some(userId))
}

/**
  * Used for interacting with FavorStatuss in the database
  * @param id FavorStatus database id
  * @param userId Id of the user linked with this FavorStatus
  * @param favorGiven Total amount of favor given by this user throughout history
  * @param favorReceived Total amount of favor received by this user from any other user throughout history
  * @param favorCirculated The amount of given favor received back throughout history
  * @param created Time when this status was registered
  * @param deprecatedAfter Time when this status was replaced with a newer version
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class FavorStatusModel(id: Option[Int] = None, userId: Option[Int] = None, 
	favorGiven: Option[Int] = None, favorReceived: Option[Int] = None, favorCirculated: Option[Int] = None, 
	created: Option[Instant] = None, deprecatedAfter: Option[Instant] = None) 
	extends StorableWithFactory[FavorStatus]
{
	// IMPLEMENTED	--------------------
	
	override def factory = FavorStatusModel.factory
	
	override def valueProperties = 
	{
		import FavorStatusModel._
		Vector("id" -> id, userIdAttName -> userId, favorGivenAttName -> favorGiven, 
			favorReceivedAttName -> favorReceived, favorCirculatedAttName -> favorCirculated, 
			createdAttName -> created, deprecatedAfterAttName -> deprecatedAfter)
	}
	
	
	// OTHER	--------------------
	
	/**
	  * @param created A new created
	  * @return A new copy of this model with the specified created
	  */
	def withCreated(created: Instant) = copy(created = Some(created))
	
	/**
	  * @param deprecatedAfter A new deprecatedAfter
	  * @return A new copy of this model with the specified deprecatedAfter
	  */
	def withDeprecatedAfter(deprecatedAfter: Instant) = copy(deprecatedAfter = Some(deprecatedAfter))
	
	/**
	  * @param favorCirculated A new favorCirculated
	  * @return A new copy of this model with the specified favorCirculated
	  */
	def withFavorCirculated(favorCirculated: Int) = copy(favorCirculated = Some(favorCirculated))
	
	/**
	  * @param favorGiven A new favorGiven
	  * @return A new copy of this model with the specified favorGiven
	  */
	def withFavorGiven(favorGiven: Int) = copy(favorGiven = Some(favorGiven))
	
	/**
	  * @param favorReceived A new favorReceived
	  * @return A new copy of this model with the specified favorReceived
	  */
	def withFavorReceived(favorReceived: Int) = copy(favorReceived = Some(favorReceived))
	
	/**
	  * @param userId A new userId
	  * @return A new copy of this model with the specified userId
	  */
	def withUserId(userId: Int) = copy(userId = Some(userId))
}


package vf.favor.database.access.single.user

import java.time.Instant
import utopia.flow.datastructure.immutable.Value
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.single.model.SingleRowModelAccess
import utopia.vault.nosql.access.template.model.DistinctModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.user.FavorStatusFactory
import vf.favor.database.model.user.FavorStatusModel
import vf.favor.model.stored.user.FavorStatus

/**
  * A common trait for access points that return individual and distinct FavorStatuss.
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait UniqueFavorStatusAccess 
	extends SingleRowModelAccess[FavorStatus] 
		with DistinctModelAccess[FavorStatus, Option[FavorStatus], Value] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * Id of the user linked with this FavorStatus. None if no instance (or value) was found.
	  */
	def userId(implicit connection: Connection) = pullColumn(model.userIdColumn).int
	
	/**
	  * Total amount of favor given by this user throughout history. None if no instance (or value) was found.
	  */
	def favorGiven(implicit connection: Connection) = pullColumn(model.favorGivenColumn).int
	
	/**
	  * Total amount of favor received by this user from any other user throughout history. None if no instance (or value) was found.
	  */
	def favorReceived(implicit connection: Connection) = pullColumn(model.favorReceivedColumn).int
	
	/**
	  * The amount of given favor received back throughout history. None if no instance (or value) was found.
	  */
	def favorCirculated(implicit connection: Connection) = pullColumn(model.favorCirculatedColumn).int
	
	/**
	  * Time when this status was registered. None if no instance (or value) was found.
	  */
	def created(implicit connection: Connection) = pullColumn(model.createdColumn).instant
	
	/**
	  * Time when this status was replaced with a newer version. None if no instance (or value) was found.
	  */
	def deprecatedAfter(implicit connection: Connection) = pullColumn(model.deprecatedAfterColumn).instant
	
	def id(implicit connection: Connection) = pullColumn(index).int
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorStatusModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorStatusFactory
	
	
	// OTHER	--------------------
	
	/**
	  * Updates the created of the targeted FavorStatus instance(s)
	  * @param newCreated A new created to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def created_=(newCreated: Instant)(implicit connection: Connection) = 
		putColumn(model.createdColumn, newCreated)
	
	/**
	  * Updates the deprecatedAfter of the targeted FavorStatus instance(s)
	  * @param newDeprecatedAfter A new deprecatedAfter to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def deprecatedAfter_=(newDeprecatedAfter: Instant)(implicit connection: Connection) = 
		putColumn(model.deprecatedAfterColumn, newDeprecatedAfter)
	
	/**
	  * Updates the favorCirculated of the targeted FavorStatus instance(s)
	  * @param newFavorCirculated A new favorCirculated to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def favorCirculated_=(newFavorCirculated: Int)(implicit connection: Connection) = 
		putColumn(model.favorCirculatedColumn, newFavorCirculated)
	
	/**
	  * Updates the favorGiven of the targeted FavorStatus instance(s)
	  * @param newFavorGiven A new favorGiven to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def favorGiven_=(newFavorGiven: Int)(implicit connection: Connection) = 
		putColumn(model.favorGivenColumn, newFavorGiven)
	
	/**
	  * Updates the favorReceived of the targeted FavorStatus instance(s)
	  * @param newFavorReceived A new favorReceived to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def favorReceived_=(newFavorReceived: Int)(implicit connection: Connection) = 
		putColumn(model.favorReceivedColumn, newFavorReceived)
	
	/**
	  * Updates the userId of the targeted FavorStatus instance(s)
	  * @param newUserId A new userId to assign
	  * @return Whether any FavorStatus instance was affected
	  */
	def userId_=(newUserId: Int)(implicit connection: Connection) = putColumn(model.userIdColumn, newUserId)
}


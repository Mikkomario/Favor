package vf.favor.database.access.many.user

import java.time.Instant
import utopia.flow.generic.ValueConversions._
import utopia.vault.database.Connection
import utopia.vault.nosql.access.many.model.ManyRowModelAccess
import utopia.vault.nosql.template.Indexed
import vf.favor.database.factory.user.FavorStatusFactory
import vf.favor.database.model.user.FavorStatusModel
import vf.favor.model.stored.user.FavorStatus

/**
  * A common trait for access points which target multiple FavorStatuss at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
trait ManyFavorStatussAccess extends ManyRowModelAccess[FavorStatus] with Indexed
{
	// COMPUTED	--------------------
	
	/**
	  * userIds of the accessible FavorStatuss
	  */
	def userIds(implicit connection: Connection) = pullColumn(model.userIdColumn)
		.flatMap { value => value.int }
	
	/**
	  * favorGiven of the accessible FavorStatuss
	  */
	def favorGiven(implicit connection: Connection) = 
		pullColumn(model.favorGivenColumn).flatMap { value => value.int }
	
	/**
	  * favorReceived of the accessible FavorStatuss
	  */
	def favorReceived(implicit connection: Connection) = 
		pullColumn(model.favorReceivedColumn).flatMap { value => value.int }
	
	/**
	  * favorCirculated of the accessible FavorStatuss
	  */
	def favorCirculated(implicit connection: Connection) = 
		pullColumn(model.favorCirculatedColumn).flatMap { value => value.int }
	
	/**
	  * createds of the accessible FavorStatuss
	  */
	def createds(implicit connection: Connection) = 
		pullColumn(model.createdColumn).flatMap { value => value.instant }
	
	/**
	  * deprecatedAfters of the accessible FavorStatuss
	  */
	def deprecatedAfters(implicit connection: Connection) = 
		pullColumn(model.deprecatedAfterColumn).flatMap { value => value.instant }
	
	def ids(implicit connection: Connection) = pullColumn(index).flatMap { id => id.int }
	
	/**
	  * Factory used for constructing database the interaction models
	  */
	protected def model = FavorStatusModel
	
	
	// IMPLEMENTED	--------------------
	
	override def factory = FavorStatusFactory
	
	override protected def defaultOrdering = Some(factory.defaultOrdering)
	
	
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


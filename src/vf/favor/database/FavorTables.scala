package vf.favor.database

import utopia.vault.model.immutable.Table

/**
  * Used for accessing the database tables introduced in this project
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object FavorTables
{
	// COMPUTED	--------------------
	
	/**
	  * Table that contains FavorEvents (Represents a recognized event where a user performs a favor to another user)
	  */
	def favorEvent = apply("favor_event")
	
	/**
	  * Table that contains FavorStatuss (Saves an user's cumulative favor status summaries (with history))
	  */
	def favorStatus = apply("favor_status")
	
	/**
	  * Table that contains Masks (Represents a codename used for a user when trust level doesn't allow use of real identities)
	  */
	def mask = apply("mask")
	
	/**
	  * Table that contains MaskUses (Records which masks have been used towards which people)
	  */
	def maskUse = apply("mask_use")
	
	/**
	  * Table that contains Messages (Represents a message from one user to another)
	  */
	def message = apply("message")
	
	/**
	  * Table that contains Recommendations (Represents a recommendation given by a user in order to introduce another user to a third party)
	  */
	def recommendation = apply("recommendation")
	
	/**
	  * Table that contains RecommendationResponses (Records the response given by the recommendation recipient)
	  */
	def recommendationResponse = apply("recommendation_response")
	
	/**
	  * Table that contains Relationships (Represents an established (directional) relationship between two users.)
	  */
	def relationship = apply("relationship")
	
	/**
	  * Table that contains RelationshipStates (Used for storing the current favor and trust states in a relationship)
	  */
	def relationshipState = apply("relationship_state")
	
	
	// OTHER	--------------------
	
	private def apply(tableName: String): Table = 
	{
		// TODO: Refer to a tables instance of your choice
		// If you're using the Citadel module, import utopia.citadel.database.Tables
		// Tables(tableName)
		???
	}
}


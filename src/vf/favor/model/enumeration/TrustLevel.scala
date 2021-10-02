package vf.favor.model.enumeration

import java.util.NoSuchElementException
import utopia.flow.util.CollectionExtensions._

/**
  * Common trait for all TrustLevel values
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
sealed trait TrustLevel
{
	// ABSTRACT	--------------------
	
	/**
	  * Id used for this value in database / SQL
	  */
	def id: Int
}

object TrustLevel
{
	// ATTRIBUTES	--------------------
	
	/**
	  * All available values of this enumeration
	  */
	val values: Vector[TrustLevel] = Vector(Broken, Suspicious, Cautious, Open, Transparent, Positive, Deep)
	
	
	// OTHER	--------------------
	
	/**
	  * @param id Id representing a TrustLevel
	  * @return TrustLevel matching that id. None if the id didn't match any TrustLevel
	  */
	def findForId(id: Int) = values.find { _.id == id }
	
	/**
	  * @param id Id matching a TrustLevel
	  * @return TrustLevel matching that id. Failure if no suitable value was found.
	  */
	def forId(id: Int) = 
		findForId(id).toTry { new NoSuchElementException(s"No value of TrustLevel matches id '$id'") }
	
	
	// NESTED	--------------------
	
	case object Broken extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 1
	}
	
	case object Cautious extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 3
	}
	
	case object Deep extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 7
	}
	
	case object Open extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 4
	}
	
	case object Positive extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 6
	}
	
	case object Suspicious extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 2
	}
	
	case object Transparent extends TrustLevel
	{
		// ATTRIBUTES	--------------------
		
		override val id = 5
	}
}


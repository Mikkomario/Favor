package vf.favor.database.factory.communication

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import vf.favor.database.FavorTables
import vf.favor.model.partial.communication.MaskUseData
import vf.favor.model.stored.communication.MaskUse

/**
  * Used for reading MaskUse data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MaskUseFactory extends FromValidatedRowModelFactory[MaskUse] with FromRowFactoryWithTimestamps[MaskUse]
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def table = FavorTables.maskUse
	
	override def fromValidatedModel(model: Model[Constant]) = 
		MaskUse(model("id"), MaskUseData(model("maskId"), model("viewerId"), model("created")))
}


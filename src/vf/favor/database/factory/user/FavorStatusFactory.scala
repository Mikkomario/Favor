package vf.favor.database.factory.user

import utopia.flow.datastructure.immutable.{Constant, Model}
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromValidatedRowModelFactory
import utopia.vault.nosql.template.Deprecatable
import vf.favor.database.FavorTables
import vf.favor.database.model.user.FavorStatusModel
import vf.favor.model.partial.user.FavorStatusData
import vf.favor.model.stored.user.FavorStatus

/**
  * Used for reading FavorStatus data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object FavorStatusFactory 
	extends FromValidatedRowModelFactory[FavorStatus] with FromRowFactoryWithTimestamps[FavorStatus] 
		with Deprecatable
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def nonDeprecatedCondition = FavorStatusModel.nonDeprecatedCondition
	
	override def table = FavorTables.favorStatus
	
	override def fromValidatedModel(model: Model[Constant]) = 
		FavorStatus(model("id"), FavorStatusData(model("userId"), model("favorGiven"), 
			model("favorReceived"), model("favorCirculated"), model("created"), model("deprecatedAfter")))
}


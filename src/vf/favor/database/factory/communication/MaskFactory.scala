package vf.favor.database.factory.communication

import utopia.flow.datastructure.template
import utopia.flow.datastructure.template.Property
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromRowModelFactory
import utopia.vault.nosql.template.Deprecatable
import vf.favor.database.FavorTables
import vf.favor.database.model.communication.MaskModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.partial.communication.MaskData
import vf.favor.model.stored.communication.Mask

/**
  * Used for reading Mask data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object MaskFactory extends FromRowModelFactory[Mask] with FromRowFactoryWithTimestamps[Mask] with Deprecatable
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def nonDeprecatedCondition = MaskModel.nonDeprecatedCondition
	
	override def table = FavorTables.mask
	
	override def apply(model: template.Model[Property]) = 
	{
		table.validate(model).map { valid => 
			val minContactTrust = valid("minContactTrust").int.flatMap(TrustLevel.findForId)
			val minIndirectTrust = valid("minIndirectTrust").int.flatMap(TrustLevel.findForId)
			Mask(valid("id"), MaskData(
				valid("userId"), valid("name"), minContactTrust, minIndirectTrust, valid("created"), 
					valid("deprecatedAfter")))
		}
	}
}


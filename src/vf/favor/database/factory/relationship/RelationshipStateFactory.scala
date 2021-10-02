package vf.favor.database.factory.relationship

import utopia.flow.datastructure.template
import utopia.flow.datastructure.template.Property
import utopia.flow.generic.ValueUnwraps._
import utopia.vault.nosql.factory.row.FromRowFactoryWithTimestamps
import utopia.vault.nosql.factory.row.model.FromRowModelFactory
import utopia.vault.nosql.template.Deprecatable
import vf.favor.database.FavorTables
import vf.favor.database.model.relationship.RelationshipStateModel
import vf.favor.model.enumeration.TrustLevel
import vf.favor.model.partial.relationship.RelationshipStateData
import vf.favor.model.stored.relationship.RelationshipState

/**
  * Used for reading RelationshipState data from the DB
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object RelationshipStateFactory 
	extends FromRowModelFactory[RelationshipState] with FromRowFactoryWithTimestamps[RelationshipState] 
		with Deprecatable
{
	// IMPLEMENTED	--------------------
	
	override def creationTimePropertyName = "created"
	
	override def nonDeprecatedCondition = RelationshipStateModel.nonDeprecatedCondition
	
	override def table = FavorTables.relationshipState
	
	override def apply(model: template.Model[Property]) = 
	{
		table.validate(model).flatMap { valid => 
			TrustLevel.forId(valid("trust").getInt).map { trust => 
				RelationshipState(valid("id"), RelationshipStateData(
					valid("relationshipId"), trust, valid("favor"), valid("created"), 
						valid("deprecatedAfter")))
			}
		}
	}
}


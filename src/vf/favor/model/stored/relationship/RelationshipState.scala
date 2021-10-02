package vf.favor.model.stored.relationship

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.relationship.RelationshipStateData

/**
  * Represents a RelationshipState that has already been stored in the database
  * @param id id of this RelationshipState in the database
  * @param data Wrapped RelationshipState data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class RelationshipState(id: Int, data: RelationshipStateData) 
	extends StoredModelConvertible[RelationshipStateData]


package vf.favor.model.stored.relationship

import utopia.vault.model.template.StoredModelConvertible
import vf.favor.model.partial.relationship.RelationshipData

/**
  * Represents a Relationship that has already been stored in the database
  * @param id id of this Relationship in the database
  * @param data Wrapped Relationship data
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
case class Relationship(id: Int, data: RelationshipData) extends StoredModelConvertible[RelationshipData]


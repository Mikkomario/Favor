package vf.favor.database.access.many.relationship

import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.model.stored.relationship.RelationshipState

/**
  * The root access point when targeting multiple RelationshipStates at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRelationshipStates extends ManyRelationshipStatesAccess with NonDeprecatedView[RelationshipState]


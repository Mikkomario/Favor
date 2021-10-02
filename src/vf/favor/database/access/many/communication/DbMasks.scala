package vf.favor.database.access.many.communication

import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.model.stored.communication.Mask

/**
  * The root access point when targeting multiple Masks at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMasks extends ManyMasksAccess with NonDeprecatedView[Mask]


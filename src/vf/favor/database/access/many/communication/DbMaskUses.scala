package vf.favor.database.access.many.communication

import utopia.vault.nosql.view.UnconditionalView

/**
  * The root access point when targeting multiple MaskUses at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMaskUses extends ManyMaskUsesAccess with UnconditionalView


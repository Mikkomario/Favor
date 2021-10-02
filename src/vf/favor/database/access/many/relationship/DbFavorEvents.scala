package vf.favor.database.access.many.relationship

import utopia.vault.nosql.view.UnconditionalView

/**
  * The root access point when targeting multiple FavorEvents at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbFavorEvents extends ManyFavorEventsAccess with UnconditionalView


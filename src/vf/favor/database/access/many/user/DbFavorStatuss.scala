package vf.favor.database.access.many.user

import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.model.stored.user.FavorStatus

/**
  * The root access point when targeting multiple FavorStatuss at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbFavorStatuss extends ManyFavorStatussAccess with NonDeprecatedView[FavorStatus]


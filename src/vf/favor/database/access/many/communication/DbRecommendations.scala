package vf.favor.database.access.many.communication

import utopia.vault.nosql.view.UnconditionalView

/**
  * The root access point when targeting multiple Recommendations at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRecommendations extends ManyRecommendationsAccess with UnconditionalView


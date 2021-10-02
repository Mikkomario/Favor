package vf.favor.database.access.many.communication

import utopia.vault.nosql.view.UnconditionalView

/**
  * The root access point when targeting multiple RecommendationResponses at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbRecommendationResponses extends ManyRecommendationResponsesAccess with UnconditionalView


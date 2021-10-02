package vf.favor.database.access.many.communication

import utopia.vault.nosql.view.NonDeprecatedView
import vf.favor.model.stored.communication.Message

/**
  * The root access point when targeting multiple Messages at a time
  * @author Mikko Hilpinen
  * @since 2021-10-02
  */
object DbMessages extends ManyMessagesAccess with NonDeprecatedView[Message]


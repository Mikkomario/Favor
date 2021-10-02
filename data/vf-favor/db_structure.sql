-- Saves an user's cumulative favor status summaries (with history)
-- user_id: Id of the user linked with this FavorStatus
-- favor_given: Total amount of favor given by this user throughout history
-- favor_received: Total amount of favor received by this user from any other user throughout history
-- favor_circulated: The amount of given favor received back throughout history
-- created: Time when this status was registered
-- deprecated_after: Time when this status was replaced with a newer version
CREATE TABLE favor_status(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`user_id` INT NOT NULL, 
	`favor_given` INT NOT NULL, 
	`favor_received` INT NOT NULL, 
	`favor_circulated` INT NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`deprecated_after` DATETIME, 
	INDEX fs_created_idx (`created)`, 
	INDEX fs_deprecated_after_idx (`deprecated_after)`, 
	CONSTRAINT fs_u_user_ref_fk FOREIGN KEY fs_u_user_ref_idx (user_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Represents an established (directional) relationship between two users.
-- user_id: Id of the primary user
-- target_user_id: Id of the user the primary user has a relationship with
-- current_favor: The primary user's favor towards the target user (cumulative)
-- created: Time when this relationship was started
CREATE TABLE relationship(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`user_id` INT NOT NULL, 
	`target_user_id` INT NOT NULL, 
	`current_favor` INT NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX r_created_idx (`created)`, 
	CONSTRAINT r_u_user_ref_fk FOREIGN KEY r_u_user_ref_idx (user_id) REFERENCES `user`(id) ON DELETE CASCADE, 
	CONSTRAINT r_u_target_user_ref_fk FOREIGN KEY r_u_target_user_ref_idx (target_user_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Used for storing the current favor and trust states in a relationship
-- relationship_id: Id of the described relationship
-- trust: The level of trust in the described relationship
-- favor: The amount of cumulated favor in this relationship
-- created: Time when this RelationshipState was first created
-- deprecated_after: Time when this RelationshipState became deprecated. None while this RelationshipState is still valid.
CREATE TABLE relationship_state(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`relationship_id` INT NOT NULL, 
	`trust` INT NOT NULL, 
	`favor` INT NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`deprecated_after` DATETIME, 
	INDEX rs_created_idx (`created)`, 
	INDEX rs_deprecated_after_idx (`deprecated_after)`, 
	CONSTRAINT rs_r_relationship_ref_fk FOREIGN KEY rs_r_relationship_ref_idx (relationship_id) REFERENCES `relationship`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Represents a recognized event where a user performs a favor to another user
-- relationship_id: Id of the relationship in which this even occurred
-- title: Title of this favor event
-- description: More detailed description of this favor event
-- created: Time when this favor was recognized
CREATE TABLE favor_event(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`relationship_id` INT NOT NULL, 
	`title` VARCHAR(255) NOT NULL, 
	`description` VARCHAR(1048) NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX fe_created_idx (`created)`, 
	CONSTRAINT fe_r_relationship_ref_fk FOREIGN KEY fe_r_relationship_ref_idx (relationship_id) REFERENCES `relationship`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Represents a codename used for a user when trust level doesn't allow use of real identities
-- user_id: Id of the user who uses this mask / alias
-- name: Alias used as the mask
-- min_contact_trust: Level of trust required for the intermediary contact user in order for this mask to be applicable
-- min_indirect_trust: Level of trust required from the intermediary contact user towards the unknown user in order for this mask to be applicable
-- created: Time when this Mask was first created
-- deprecated_after: Time after which the owner decided to stop using this mask
CREATE TABLE mask(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`user_id` INT NOT NULL, 
	`name` VARCHAR(32) NOT NULL, 
	`min_contact_trust` INT, 
	`min_indirect_trust` INT, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`deprecated_after` DATETIME, 
	INDEX m_created_idx (`created)`, 
	INDEX m_deprecated_after_idx (`deprecated_after)`, 
	CONSTRAINT m_u_user_ref_fk FOREIGN KEY m_u_user_ref_idx (user_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Records which masks have been used towards which people
-- mask_id: Id of the mask linked with this MaskUse
-- viewer_id: Id of the user who sees this mask
-- created: Time when this MaskUse was first created
CREATE TABLE mask_use(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`mask_id` INT NOT NULL, 
	`viewer_id` INT NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX mu_created_idx (`created)`, 
	CONSTRAINT mu_m_mask_ref_fk FOREIGN KEY mu_m_mask_ref_idx (mask_id) REFERENCES `mask`(id) ON DELETE CASCADE, 
	CONSTRAINT mu_u_viewer_ref_fk FOREIGN KEY mu_u_viewer_ref_idx (viewer_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Represents a message from one user to another
-- sender_id: Id of the user who sent this message
-- recipient_id: Id of the user who received this message
-- text: Message content
-- expires: Time when this message expires / should be deleted
-- created: Time when this message was sent
CREATE TABLE message(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`sender_id` INT NOT NULL, 
	`recipient_id` INT NOT NULL, 
	`text` VARCHAR(1048) NOT NULL, 
	`expires` DATETIME NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX m_expires_idx (`expires)`, 
	INDEX m_created_idx (`created)`, 
	CONSTRAINT m_u_sender_ref_fk FOREIGN KEY m_u_sender_ref_idx (sender_id) REFERENCES `user`(id) ON DELETE CASCADE, 
	CONSTRAINT m_u_recipient_ref_fk FOREIGN KEY m_u_recipient_ref_idx (recipient_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Represents a recommendation given by a user in order to introduce another user to a third party
-- sender_id: Id of the user doing the recommending
-- recipient_id: Id of the user receiving the recommendation
-- recommended_id: Id of the user being recommended
-- message: Message sent along with the recommendation
-- created: Time when this recommendation was sent
CREATE TABLE recommendation(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`sender_id` INT NOT NULL, 
	`recipient_id` INT NOT NULL, 
	`recommended_id` INT NOT NULL, 
	`message` VARCHAR(1048) NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX r_created_idx (`created)`, 
	CONSTRAINT r_u_sender_ref_fk FOREIGN KEY r_u_sender_ref_idx (sender_id) REFERENCES `user`(id) ON DELETE CASCADE, 
	CONSTRAINT r_u_recipient_ref_fk FOREIGN KEY r_u_recipient_ref_idx (recipient_id) REFERENCES `user`(id) ON DELETE CASCADE, 
	CONSTRAINT r_u_recommended_ref_fk FOREIGN KEY r_u_recommended_ref_idx (recommended_id) REFERENCES `user`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- Records the response given by the recommendation recipient
-- recommendation_id: Id of the recommendation this response is for
-- new_trust_level: Granted level of trust from the person receiving the recommendation. None if no trust was granted.
-- message: Written response to the recommendation
-- created: Time when this RecommendationResponse was first created
CREATE TABLE recommendation_response(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`recommendation_id` INT NOT NULL, 
	`new_trust_level` INT NOT NULL, 
	`message` VARCHAR(1048) NOT NULL, 
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	INDEX rr_created_idx (`created)`, 
	CONSTRAINT rr_r_recommendation_ref_fk FOREIGN KEY rr_r_recommendation_ref_idx (recommendation_id) REFERENCES `recommendation`(id) ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;


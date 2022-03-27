package com.hosting.rest.api.models.User.HostLanguage;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "HOST_LANGUAGE")
public class HostLanguageModel {

	@EmbeddedId
	private HostLanguageLanguageId hostLanguageLanguageId;

	@Column(name = "IS_NATIVE")
	private boolean isNative;

}

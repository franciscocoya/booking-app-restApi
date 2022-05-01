package com.hosting.rest.api.models.User.HostLanguage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOST_LANGUAGE")
public class HostLanguageModel implements Serializable{

	private static final long serialVersionUID = -3056418649779554220L;

	@EmbeddedId
	private HostLanguageLanguageId idHostLanguage;
	
	@Column(name = "IS_NATIVE")
	private boolean isNative;

}

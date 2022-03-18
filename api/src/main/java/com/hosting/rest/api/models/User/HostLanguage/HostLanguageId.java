package com.hosting.rest.api.models.User.HostLanguage;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HostLanguageId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idHost;
    private int idLang;
}

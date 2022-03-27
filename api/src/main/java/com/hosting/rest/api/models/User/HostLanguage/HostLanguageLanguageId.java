package com.hosting.rest.api.models.User.HostLanguage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class HostLanguageLanguageId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "ID_HOST")
    private int idHost;

    @Column(name = "ID_LANG")
    private int idLang;

}

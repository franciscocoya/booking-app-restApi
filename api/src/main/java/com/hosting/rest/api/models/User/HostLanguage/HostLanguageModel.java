package com.hosting.rest.api.models.User.HostLanguage;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "HOST_LANGUAGE")
@IdClass(HostLanguageId.class)
public class HostLanguageModel {
	
	@Id
    @Column(name = "ID_HOST")
    private int idHost;

    @Id
    @Column(name = "ID_LANG")
    private int idLang;

    @Column(name = "IS_NATIVE")
    private boolean isNative;

}

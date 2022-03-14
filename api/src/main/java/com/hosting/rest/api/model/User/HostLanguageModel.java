package com.hosting.rest.api.model.User;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "HOST_LANGUAGE")
public class HostLanguageModel {
    @Column(name = "ID_HOST")
    private int idHost;

    @Id
    @Column(name = "ID_LANG")
    private int idLang;

    @Id
    @Column(name = "IS_NATIVE")
    private boolean isNative;

}

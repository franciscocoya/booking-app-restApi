package com.hosting.rest.api.models.Language;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "APP_LANGUAGE" )
@Data
public class LanguageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Enumerated
    @Column(name = "LANG")
    private Language language;

}

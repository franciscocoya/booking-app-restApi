package com.hosting.rest.api.models.Language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "APP_LANGUAGE" )
public class LanguageModel implements Serializable{

    private static final long serialVersionUID = 386718704217054232L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG")
    private Language language;

}

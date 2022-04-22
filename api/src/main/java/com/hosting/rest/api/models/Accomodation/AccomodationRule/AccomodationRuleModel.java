package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.1
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOMODATION_RULE")
public class AccomodationRuleModel implements Serializable {

    private static final long serialVersionUID = -5243590824673911739L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RULE")
    private String rule;
}

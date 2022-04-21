package com.hosting.rest.api.models.Accomodation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "ACCOMODATION_CATEGORY" )
public class AccomodationCategoryModel implements Serializable{

    private static final long serialVersionUID = -1765348609485406739L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ACC_CAT")
    private String accomodationCategory;
}

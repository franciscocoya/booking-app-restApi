package com.hosting.rest.api.models.Accomodation.SavedAccomodation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SavedAccomodationId implements Serializable {

    @Column(name = "ID_ACC")
    private String accomodationId;

    @Column(name = "ID_USER")
    private Integer userHostId;
}

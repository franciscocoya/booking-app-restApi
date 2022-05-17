package com.hosting.rest.api.models.User.UserConfiguration;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hosting.rest.api.models.Currency.CurrencyModel;
import com.hosting.rest.api.models.Language.LanguageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_CONFIGURATION")
public class UserConfigurationModel implements Serializable{
	
	private static final long serialVersionUID = 3521673215555991129L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idUserConfiguration;

	@ManyToOne
	@JoinColumn(name = "ID_APP_LANGUAGE")
	private LanguageModel idLanguage;

	@ManyToOne
	@JoinColumn(name = "ID_CURRENCY")
	private CurrencyModel idCurrency;
	
	public UserConfigurationModel(LanguageModel idLanguage, CurrencyModel idCurrency) {
		this.idLanguage = idLanguage;
		this.idCurrency = idCurrency;
	}
}

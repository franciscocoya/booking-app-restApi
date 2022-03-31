package com.hosting.rest.api.models.User.UserConfiguration;

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

import lombok.Data;

@Entity
@Data
@Table(name = "USER_CONFIGURATION")
public class UserConfigurationModel {

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
}

package com.unicesumar.AEP42.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AutorEntity {
	
	private String primeiroNome;
	private String ultimoNome;
	
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
}

package com.bank.app.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bank {
	@Id
	private String idBank;
	@NotEmpty(message = "La descripcion del banco no puede estar vacio")
	private String description;
	@NotNull(message = "El codigo del tipo de banco no puede estar vacio")
	private Integer codeBank;
	@NotEmpty(message = "La direccion del banco no puede estar vacio")
	private String address;
	public Bank(@NotEmpty(message = "La descripcion del banco no puede estar vacio") String description,
			@NotNull(message = "El codigo del tipo de banco no puede estar vacio") Integer codeBank,
			@NotEmpty(message = "La direccion del banco no puede estar vacio") String address) {
		super();
		this.description = description;
		this.codeBank = codeBank;
		this.address = address;
	}

}

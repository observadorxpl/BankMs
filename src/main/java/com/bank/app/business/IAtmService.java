package com.bank.app.business;

import com.bank.app.models.Atm;
import com.bank.app.util.ICRUD;

import reactor.core.publisher.Mono;

public interface IAtmService extends ICRUD<Atm>{
	public Mono<Atm> buscarPorCodeAtm(Integer codeAtm);
}

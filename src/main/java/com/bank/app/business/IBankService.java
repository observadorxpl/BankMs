package com.bank.app.business;

import com.bank.app.models.Bank;
import com.bank.app.util.ICRUD;

import reactor.core.publisher.Mono;

public interface IBankService extends ICRUD<Bank>{
	public Mono<Bank> buscarPorCodeBank(Integer codeBank);

}

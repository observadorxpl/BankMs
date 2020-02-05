package com.bank.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.models.Bank;
import com.bank.app.repository.IAtmRepository;
import com.bank.app.repository.IBankRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class BankServiceImpl implements IBankService{
	@Autowired
	private IBankRepository bankRepository;
	
	@Override
	public Flux<Bank> findAll() {
		return bankRepository.findAll();
	}

	@Override
	public Mono<Bank> finById(String id) {
		return bankRepository.findById(id);
	}

	@Override
	public Mono<Bank> save(Bank t) {
		return bankRepository.save(t);
	}
	
	@Override
	public Mono<Bank> update(Bank t) {
		return bankRepository.save(t);
	}

	
	@Override
	public Mono<Void> delete(Bank t) {
		return bankRepository.delete(t);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return bankRepository.deleteById(id);
	}

	@Override
	public Mono<Bank> buscarPorCodeBank(Integer codeBank) {
		return bankRepository.findByCodeBank(codeBank);
	}
}

package com.bank.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.models.Bank;

import reactor.core.publisher.Mono;
@Repository
public interface IBankRepository extends ReactiveMongoRepository<Bank, String>{
	Mono<Bank> findByCodeBank(Integer codeBank);
}

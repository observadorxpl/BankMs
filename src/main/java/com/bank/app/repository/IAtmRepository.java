package com.bank.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.models.Atm;

import reactor.core.publisher.Mono;
@Repository
public interface IAtmRepository extends ReactiveMongoRepository<Atm, String>{
	Mono<Atm> findByCodeAtm(Integer codeAtm);

}

package com.bank.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bank.app.models.Atm;
import com.bank.app.models.Bank;
import com.bank.app.repository.IAtmRepository;
import com.bank.app.repository.IBankRepository;

import reactor.core.publisher.Flux;
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class BankMsApplication implements CommandLineRunner {
	@Autowired
	private ReactiveMongoTemplate template;

	@Autowired
	private IBankRepository bankRepository;
	
	@Autowired
	private IAtmRepository atmRepo;

	public static void main(String[] args) {
		SpringApplication.run(BankMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bankRepository.findByCodeBank(100).flatMap(bank -> {
			return atmRepo.save(new Atm(bank, 100, "Magdalena 1032"));
		}).flatMap(atm -> {
			return atmRepo.save(new Atm(atm.getBank(), 101, "Javier Prado 1030"));
		}).subscribe();
		
		bankRepository.findByCodeBank(101).flatMap(bank -> {
			return atmRepo.save(new Atm(bank, 102, "Jiron de la union 423"));
		}).flatMap(atm -> {
			return atmRepo.save(new Atm(atm.getBank(), 103, "Javier Prado 1030"));
		}).subscribe();
		
		bankRepository.findByCodeBank(102).flatMap(bank -> {
			return atmRepo.save(new Atm(bank, 104, "Av arequipa 112"));
		}).flatMap(atm -> {
			return atmRepo.save(new Atm(atm.getBank(), 105, "Av Brasil 1314"));
		}).subscribe();
		/*
		template.dropCollection(Bank.class).subscribe();

		Bank b1 = new Bank("BBVA CONTINENTAL", 100, "Av. Javier Prado Oeste 102");
		Bank b2 = new Bank("BCP CONTINENTAL", 101, "Av. Javier Prado Oeste 607");
		Bank b3 = new Bank("INTERBANK", 102, "Jr. Bolognesi 0104");
		Flux.just(b1, b2, b3).flatMap(bank -> bankRepository.save(bank))
				.subscribe(res -> System.out.println("Banco: " + res + " registrado"));
				*/
	}

}

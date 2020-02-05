package com.bank.app;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bank.app.business.IBankService;
import com.bank.app.models.Bank;

import reactor.core.publisher.Mono;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankMsApplicationTests {
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private IBankService bankService;
	
	@Test
	void findAllBanksTest() {
		client.get()
		.uri("/bank")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Bank.class)
		.consumeWith(response -> {
			List<Bank> bancos = response.getResponseBody();
			bancos.forEach(b -> {
				System.out.println(b.getDescription());
			});
			
			Assertions.assertThat(bancos.size()>0).isTrue();
		});
	}
	@Test
	void findBankById() {
		client.get()
		.uri("/bank" + "/{id}", Collections.singletonMap("id", "5e2f4a13b6cda44a98ee8abd"))
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank bank = response.getResponseBody();
			System.out.println("[Banco: ENCONTRADO, ID]" + bank);
			Assertions.assertThat(bank.getCodeBank()).isNotNull().isEqualTo(100);
			Assertions.assertThat(bank.getDescription()).isNotEmpty().isEqualTo("BBVA CONTINENTAL");
			Assertions.assertThat(bank.getAddress()).isNotEmpty().isEqualTo("Av. Javier Prado Oeste 102");
		});
	}
	
	@Test
	void findBankByCodeBank() {
		client.get()
		.uri("bank/code-bank" + "/{code}", Collections.singletonMap("code", "101"))
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank bank = response.getResponseBody();
			System.out.println("[Banco ENCONTRADO, CODE] " + bank);
			Assertions.assertThat(bank.getCodeBank()).isNotNull().isEqualTo(101);
			Assertions.assertThat(bank.getDescription()).isNotEmpty().isEqualTo("BCP CONTINENTAL");
			Assertions.assertThat(bank.getAddress()).isNotEmpty().isEqualTo("Av. Javier Prado Oeste 607");
		});
	}
	
	@Test
	void saveBank() {
		Bank bank = new Bank("SCOTIABANK", 103, "Jr de la Union 103");
		client.post()
		.uri("/bank")
		.body(Mono.just(bank), Bank.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank b = response.getResponseBody();
			System.out.println("[Banco REGISTRADO] " + bank);
			Assertions.assertThat(b.getCodeBank()).isNotNull().isEqualTo(103);
			Assertions.assertThat(b.getDescription()).isNotEmpty().isEqualTo("SCOTIABANK");
			Assertions.assertThat(b.getAddress()).isNotEmpty().isEqualTo("Jr de la Union 103");
		});
	}
	
	@Test
	void updateBank() {
		Bank bank= bankService.buscarPorCodeBank(103).block();
		bank.setDescription("FALABELLA PERU");
		bank.setCodeBank(104);
		bank.setAddress("Av. Javier Prado este 4200");
		
		client.put()
		.uri("/bank")
		.body(Mono.just(bank), Bank.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Bank.class)
		.consumeWith(response -> {
			Bank b = response.getResponseBody();
			System.out.println("[Banco EDITADO] " + bank);
			Assertions.assertThat(b.getCodeBank()).isNotNull().isEqualTo(104);
			Assertions.assertThat(b.getDescription()).isNotEmpty().isEqualTo("FALABELLA PERU");
			Assertions.assertThat(b.getAddress()).isNotEmpty().isEqualTo("Av. Javier Prado este 4200");
		});
	}
	
	@Test
	void deleteBank() {
		Bank bank= bankService.buscarPorCodeBank(104).block();
		
		client.delete()
		.uri("/bank" + "/{id}", Collections.singletonMap("id", bank.getIdBank()))
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.isEmpty();
	}
}

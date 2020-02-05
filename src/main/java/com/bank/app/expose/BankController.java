package com.bank.app.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.business.IBankService;
import com.bank.app.models.Bank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@Api(value = "Bank Microservice")
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private IBankService bankService;

	@GetMapping
	@ApiOperation(value = "List all banks", notes="Find all banks")
	public Flux<Bank> findAllProducts() {
		return bankService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find a bank", notes="Find a bank by id")
	public Mono<Bank> findProduct(@PathVariable String id) {
		return bankService.finById(id);
	}
	
	@GetMapping("/code-bank/{code}")
	@ApiOperation(value = "Find a bank", notes="Find a bank by codeBank: unique")
	public Mono<Bank> findByCodeBank(@PathVariable("code") Integer code) {
		return bankService.buscarPorCodeBank(code);
	}

	@PostMapping
	@ApiOperation(value = "Save a bank", notes="Save and return a bank, set a codeBank unique")
	public Mono<Bank> saveBank(@RequestBody Bank bank) {
		return bankService.save(bank);
	}

	@PutMapping
	@ApiOperation(value = "Update a bank", notes="Update and return a bank, set a codeBank unique")
	public Mono<Bank> updateBank(@RequestBody Bank bank) {
		return bankService.save(bank);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a bank", notes="Delete a bank by id")
	public Mono<Void> deleteBank(@PathVariable String id){
		return bankService.deleteById(id);
	}
}

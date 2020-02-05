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

import com.bank.app.business.IAtmService;
import com.bank.app.models.Atm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@Api(value = "Atm Microservice")
@RequestMapping("/atm")
public class AtmController {
	@Autowired
	private IAtmService atmService;

	@GetMapping
	@ApiOperation(value = "List all atms", notes="Find all atms")
	public Flux<Atm> findAllProducts() {
		return atmService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find a atm", notes="Find a atm by id")
	public Mono<Atm> findProduct(@PathVariable String id) {
		return atmService.finById(id);
	}
	
	@GetMapping("/code-Atm/{code}")
	@ApiOperation(value = "Find a atm", notes="Find a atm by codeAtm: unique")
	public Mono<Atm> findBycodeAtm(@PathVariable("code") Integer code) {
		return atmService.buscarPorCodeAtm(code);
	}

	@PostMapping
	@ApiOperation(value = "Save a atm", notes="Save and return a atm, set a codeAtm unique")
	public Mono<Atm> saveAtm(@RequestBody Atm Atm) {
		return atmService.save(Atm);
	}

	@PutMapping
	@ApiOperation(value = "Update a atm", notes="Update and return a atm, set a codeAtm unique")
	public Mono<Atm> updateAtm(@RequestBody Atm Atm) {
		return atmService.save(Atm);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a Atm", notes="Delete a Atm by id")
	public Mono<Void> deleteAtm(@PathVariable String id){
		return atmService.deleteById(id);
	}
}

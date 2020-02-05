package com.bank.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.models.Atm;
import com.bank.app.repository.IAtmRepository;
import com.bank.app.repository.IBankRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtmServiceImpl implements IAtmService{
	@Autowired
	private IAtmRepository atmRepo;

	@Autowired
	private IBankRepository bankRepo;
	@Override
	public Flux<Atm> findAll() {
		return atmRepo.findAll();
	}	

	@Override
	public Mono<Atm> finById(String id) {
		return atmRepo.findById(id);
	}

	@Override
	public Mono<Atm> save(Atm t) {
		return bankRepo.findById(t.getBank().getIdBank()).flatMap(bank -> {
			t.setBank(bank);
			return atmRepo.save(t);
		});
	}

	@Override
	public Mono<Atm> update(Atm t) {
		return bankRepo.findById(t.getBank().getIdBank()).flatMap(bank -> {
			t.setBank(bank);
			return atmRepo.save(t);
		});
	}

	@Override
	public Mono<Void> delete(Atm t) {
		return atmRepo.delete(t);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return atmRepo.deleteById(id);
	}

	@Override
	public Mono<Atm> buscarPorCodeAtm(Integer codeAtm) {
		return atmRepo.findByCodeAtm(codeAtm);
	}

}

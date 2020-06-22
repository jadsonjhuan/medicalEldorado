package br.com.medical.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.medical.exeption.ModelAlreadyExistException;
import br.com.medical.exeption.ModelNotFoundException;
import br.com.medical.model.Compromise;
import br.com.medical.model.User;
import br.com.medical.repository.CompromiseRepository;
import br.com.medical.services.CompromiseService;

@RestController
@RequestMapping("/compromises")
@CrossOrigin("*")
public class CompromiseController {

	@Autowired
	private CompromiseRepository compromiseRepo;

	@Autowired
	private CompromiseService compromiseService;

	@GetMapping(produces = "application/json")
	public List<Compromise> view() {
		return compromiseRepo.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Compromise> view(@PathVariable("id") Long id) {
		Optional<Compromise> compromiseOp = compromiseRepo.findById(id);
		if (!compromiseOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		return new ResponseEntity<Compromise>(compromiseOp.get(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Compromise> update(Compromise compromise) {
		Optional<Compromise> compromiseOp = compromiseRepo.findById(compromise.getId());
		if (!compromiseOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + compromise.getId() + " não encontrado!");
		}

		compromiseRepo.save(compromise);

		return new ResponseEntity<Compromise>(compromise, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Compromise> delete(@PathVariable("id") Long id) {
		Optional<Compromise> compromiseOp = compromiseRepo.findById(id);
		if (!compromiseOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		Compromise compromise = compromiseOp.get();

		compromiseRepo.deleteById(compromise.getId());

		return new ResponseEntity<Compromise>(compromise, HttpStatus.OK);
	}

	/**
	 * Realiza o agendamento na data e hora informado, utilizando o usuário em sessão.
	 * @param compromise
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/schedule")
	public ResponseEntity<Compromise> schedule(@RequestBody Compromise compromise, HttpSession session) {

		if (compromiseService.isAvailable(compromise.getDoctor(), compromise.getDateScheduled())) {
			User user = (User) session.getAttribute("user");
			if(user == null) {
				throw new ModelNotFoundException("Usuário não informado!");
			}
			compromise.setUser(user);
			compromise.setCreated(new Date());
			compromiseRepo.save(compromise);
			return new ResponseEntity<Compromise>(compromise, HttpStatus.OK);
		} else {
			throw new ModelAlreadyExistException("Horário indisponível para este médico!");
		}

	}
}

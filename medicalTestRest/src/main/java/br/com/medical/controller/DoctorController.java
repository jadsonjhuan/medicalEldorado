package br.com.medical.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import br.com.medical.model.Doctor;
import br.com.medical.repository.DoctorRepository;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepo;

	@GetMapping(produces = "application/json")
	public List<Doctor> view() {
		return doctorRepo.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Doctor> view(@PathVariable("id") Long id) {
		Optional<Doctor> doctorOp = doctorRepo.findById(id);
		if (!doctorOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		return new ResponseEntity<Doctor>(doctorOp.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Doctor> insert(@RequestBody @Valid Doctor doctor) {
		Doctor doctorExist = doctorRepo.findByCrm(doctor.getCrm());
		if (doctorExist != null) {
			throw new ModelAlreadyExistException("CRM " + doctor.getCrm() + " já existente!");
		}

		doctorRepo.save(doctor);

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Doctor> update(@RequestBody @Valid Doctor doctor) {
		Optional<Doctor> doctorOp = doctorRepo.findById(doctor.getId());
		if (!doctorOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + doctor.getId() + " não encontrado!");
		}

		Doctor doctorEmailExist = doctorRepo.findByCrm(doctor.getCrm());
		if (doctorEmailExist != null) {
			if (!doctorEmailExist.getId().equals(doctorOp.get().getId())) {
				throw new ModelAlreadyExistException("CRM " + doctor.getCrm() + " atribuido à outro usuário!");
			}
		}
		;

		doctorRepo.save(doctor);

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Doctor> delete(@PathVariable("id") Long id) {
		Optional<Doctor> doctorOp = doctorRepo.findById(id);
		if (!doctorOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		Doctor doctor = doctorOp.get();

		doctorRepo.deleteById(doctor.getId());

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

}

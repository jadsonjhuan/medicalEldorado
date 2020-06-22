package br.com.medical.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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

import br.com.medical.model.User;
import br.com.medical.repository.UserRepository;
import br.com.medical.services.UserService;
import br.com.medical.exeption.ModelAlreadyExistException;
import br.com.medical.exeption.ModelNotFoundException;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@GetMapping(produces = "application/json")
	public List<User> view() {
		return userRepo.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> view(@PathVariable("id") Long id) {
		Optional<User> userOp = userRepo.findById(id);
		if (!userOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		return new ResponseEntity<User>(userOp.get(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		Optional<User> userOp = userRepo.findById(user.getId());
		if (!userOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + user.getId() + " não encontrado!");
		}

		User userEmailExist = userRepo.findByEmail(user.getEmail());
		if (userEmailExist != null) {
			if (!userEmailExist.getId().equals(userOp.get().getId())) {
				throw new ModelAlreadyExistException("E-mail " + user.getEmail() + " atribuido à outro usuário!");
			}
		}

		userRepo.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> delete(@PathVariable("id") Long id) {
		Optional<User> userOp = userRepo.findById(id);
		if (!userOp.isPresent()) {
			throw new ModelNotFoundException("Registro ID:" + id + " não encontrado!");
		}

		User user = userOp.get();

		userRepo.deleteById(user.getId());

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(value = "/valid-session")
	public ResponseEntity<User> inSession(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<User>((User) session.getAttribute("user"), HttpStatus.OK);
	}

	@PostMapping(value = "signup")
	public ResponseEntity<User> signUp(@RequestBody @Valid User user, HttpSession session) {
		User userExist = userRepo.findByEmail(user.getEmail());
		if (userExist != null) {
			throw new ModelAlreadyExistException("E-mail " + user.getEmail() + " já existente!");
		}

		userRepo.save(user);
		
		session.setAttribute("user", user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/signin")
	public ResponseEntity<User> signIn(@RequestBody User user, HttpSession session) {
		User userValid = userService.signIn(user.getEmail(), session);
		return new ResponseEntity<User>(userValid, HttpStatus.OK);
	}

	@PostMapping(value = "/signout")
	public ResponseEntity<?> signOut(HttpSession session) {
		userService.signOut(session);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}

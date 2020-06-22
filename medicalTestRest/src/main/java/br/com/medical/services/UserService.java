package br.com.medical.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.medical.exeption.ModelAlreadyExistException;
import br.com.medical.model.User;
import br.com.medical.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User signIn(String userEmail, HttpSession session) {
		User user = userRepo.findByEmail(userEmail);
		if (user == null) {
			throw new ModelAlreadyExistException("E-mail não encontrado!");
		} else {
			session.setAttribute("user", user);
		}
		return user;
	}
	
	public User signUp(User user, HttpSession session) {
		User userExist = userRepo.findByEmail(user.getEmail());
		if (userExist != null) {
			throw new ModelAlreadyExistException("E-mail " + user.getEmail() + " já existente!");
		};
		
		userRepo.save(user);
		
		session.setAttribute("user", user);
		
		return user;
	}
	
	public void signOut(HttpSession session) {
		session.invalidate();
	}
}

package br.com.medical.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.medical.model.Doctor;
import br.com.medical.repository.CompromiseRepository;

@Service
public class CompromiseService {
	
	@Autowired
	private CompromiseRepository compromiseRepo;
	
	public Boolean isAvailable(Doctor doctor, Date dateScheduled) {
		System.out.println(dateScheduled);
		System.out.println(doctor.getId());
		Long count = compromiseRepo.dateScheduledIsAvailable(doctor.getId(), dateScheduled);
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

}

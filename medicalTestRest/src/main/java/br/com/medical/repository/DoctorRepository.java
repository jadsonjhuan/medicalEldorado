package br.com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medical.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	public Doctor findByCrm(String crm);
}

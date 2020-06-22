package br.com.medical.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.medical.model.Compromise;

public interface CompromiseRepository extends JpaRepository<Compromise, Long> {
	
	@Query("SELECT count(*) FROM Compromise WHERE doctor.id = :doctorId and dateScheduled = :dateScheduled")
	public Long dateScheduledIsAvailable(@Param("doctorId") Long doctorId,@Param("dateScheduled") Date dateScheduled);
}

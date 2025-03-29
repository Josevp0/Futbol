package com.josebd.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josebd.app.modelo.Competicion;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
	
}

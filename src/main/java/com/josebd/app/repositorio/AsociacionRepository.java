package com.josebd.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josebd.app.modelo.Asociacion;

public interface AsociacionRepository extends JpaRepository<Asociacion, Long> {
	
}

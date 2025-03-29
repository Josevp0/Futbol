package com.josebd.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josebd.app.modelo.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}
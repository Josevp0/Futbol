package com.josebd.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josebd.app.modelo.Club;

@Repository
public interface ClubRepositorio extends JpaRepository<Club, Long> {
}

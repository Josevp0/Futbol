package com.josebd.app.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josebd.app.modelo.Club;
import com.josebd.app.repositorio.ClubRepositorio;

@Service
public class ClubServicio {

    @Autowired
    private ClubRepositorio clubRepositorio;
    public List<Club> obtenerTodos() {
        return clubRepositorio.findAll();
    }

   
    public Club obtenerPorId(Long id) { 
        return clubRepositorio.findById(id).orElse(null);
    }

    public Club guardar(Club club) {
        return clubRepositorio.save(club);
    }

    public void eliminar(Long id) { 
        clubRepositorio.deleteById(id);
    }
}

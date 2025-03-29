package com.josebd.app.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.josebd.app.modelo.Jugador;
import com.josebd.app.repositorio.JugadorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepositorio;

    public List<Jugador> obtenerTodos() {
        return jugadorRepositorio.findAll();
    }

    public Jugador obtenerPorId(Long id) {
        Optional<Jugador> jugador = jugadorRepositorio.findById(id);
        return jugador.orElse(null); 
    }

    public Jugador guardar(Jugador jugador) {
        return jugadorRepositorio.save(jugador);
    }

    public void eliminar(Long id) {
        jugadorRepositorio.deleteById(id);
    }
}

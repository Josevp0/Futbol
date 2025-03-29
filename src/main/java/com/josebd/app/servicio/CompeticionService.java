package com.josebd.app.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.josebd.app.modelo.Competicion;
import com.josebd.app.repositorio.CompeticionRepository;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepositorio;

    public List<Competicion> listarTodas() {
        return competicionRepositorio.findAll();
    }

    public Competicion guardar(Competicion competicion) {
        return competicionRepositorio.save(competicion);
    }

    public Competicion obtenerPorId(Long id) {
        return competicionRepositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        competicionRepositorio.deleteById(id);
    }
}

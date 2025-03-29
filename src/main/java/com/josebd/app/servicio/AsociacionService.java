package com.josebd.app.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josebd.app.modelo.Asociacion;
import com.josebd.app.repositorio.AsociacionRepository;

@Service
public class AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    public List<Asociacion> listarTodas() {
        return asociacionRepository.findAll();
    }

    public Optional<Asociacion> buscarPorId(Long id) {
        return asociacionRepository.findById(id);
    }

    public Asociacion guardar(Asociacion asociacion) {
        return asociacionRepository.save(asociacion);
    }

    public void eliminarPorId(Long id) {
        asociacionRepository.deleteById(id);
    }
}

package com.josebd.app.controlador;

import com.josebd.app.modelo.Entrenador;
import com.josebd.app.servicio.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public String listarEntrenadores(Model model) {
        List<Entrenador> entrenadores = entrenadorService.findAll();
        model.addAttribute("entrenadores", entrenadores);
        return "entrenador/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        if (entrenador.getId() != null && entrenadorService.findById(entrenador.getId()).isPresent()) {
            Entrenador entrenadorExistente = entrenadorService.findById(entrenador.getId()).get();
            entrenadorExistente.setNombre(entrenador.getNombre());
            entrenadorExistente.setApellido(entrenador.getApellido());
            entrenadorExistente.setEdad(entrenador.getEdad());
            entrenadorExistente.setNacionalidad(entrenador.getNacionalidad());
            entrenadorService.save(entrenadorExistente);
        } else {
            entrenadorService.save(entrenador);
        }
        return "redirect:/entrenador";
    }

    @GetMapping("/editar/{id}")
    public String editarEntrenador(@PathVariable Long id, Model model) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        if (entrenador.isPresent()) {
            model.addAttribute("entrenador", entrenador.get());
            return "entrenador/formulario";
        }
        return "redirect:/entrenador";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.deleteById(id);
        return "redirect:/entrenador";
    }
}
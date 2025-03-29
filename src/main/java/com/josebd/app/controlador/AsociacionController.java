package com.josebd.app.controlador;

import com.josebd.app.modelo.Asociacion;
import com.josebd.app.servicio.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionService asociacionService;

    @GetMapping
    public String listarAsociaciones(Model model) {
        List<Asociacion> asociaciones = asociacionService.listarTodas();
        model.addAttribute("asociaciones", asociaciones);
        return "asociacion/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        if (asociacion.getId() != null) {
            Optional<Asociacion> asociacionExistente = asociacionService.buscarPorId(asociacion.getId());
            if (asociacionExistente.isPresent()) {
                Asociacion existente = asociacionExistente.get();
                existente.setNombre(asociacion.getNombre());
                existente.setPais(asociacion.getPais());
                existente.setPresidente(asociacion.getPresidente());
                asociacionService.guardar(existente);
            } else {
                asociacionService.guardar(asociacion);
            }
        } else {
            asociacionService.guardar(asociacion);
        }
        return "redirect:/asociacion";
    }

    @GetMapping("/editar/{id}")
    public String editarAsociacion(@PathVariable Long id, Model model) {
        Optional<Asociacion> asociacion = asociacionService.buscarPorId(id);
        if (asociacion.isPresent()) {
            model.addAttribute("asociacion", asociacion.get());
            return "asociacion/formulario";
        }
        return "redirect:/asociacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionService.eliminarPorId(id);
        return "redirect:/asociacion";
    }
}

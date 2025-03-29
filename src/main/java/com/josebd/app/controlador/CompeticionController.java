package com.josebd.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.josebd.app.modelo.Competicion;
import com.josebd.app.servicio.CompeticionService;

@Controller
public class CompeticionController {

    @Autowired
    private CompeticionService competicionServicio;

    @GetMapping("/competiciones")
    public String listarCompeticiones(Model modelo) {
        modelo.addAttribute("competiciones", competicionServicio.listarTodas());
        return "competiciones/lista";
    }

    @GetMapping("/competicion/formulario")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("competicion", new Competicion());
        return "competiciones/formulario";
    }

    @GetMapping("/competicion/editar/{id}")
    public String editarCompeticion(@PathVariable Long id, Model modelo) {
        Competicion competicion = competicionServicio.obtenerPorId(id);
        if (competicion != null) {
            modelo.addAttribute("competicion", competicion);
            return "competiciones/formulario";
        } else {
            return "redirect:/competiciones";
        }
    }

    @PostMapping("/competicion/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionServicio.guardar(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/competicion/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionServicio.eliminar(id);
        return "redirect:/competiciones";
    }
}

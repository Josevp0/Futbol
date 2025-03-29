package com.josebd.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.josebd.app.modelo.Club;
import com.josebd.app.servicio.ClubServicio;
import com.josebd.app.servicio.EntrenadorService;
import com.josebd.app.servicio.AsociacionService;
import com.josebd.app.servicio.CompeticionService;

@Controller
@RequestMapping("/clubes")
public class ClubControlador {

    @Autowired
    private ClubServicio clubServicio;

    @Autowired
    private EntrenadorService entrenadorServicio;

    @Autowired
    private AsociacionService asociacionServicio;

    @Autowired
    private CompeticionService competicionServicio;

    @GetMapping
    public String listaClubes(Model model) {
        model.addAttribute("clubes", clubServicio.obtenerTodos());
        return "clubes/lista";  
    }

    @GetMapping("/nuevo")
    public String nuevoClubForm(Model model) {
        model.addAttribute("club", new Club());
        cargarListasRelacionadas(model);
        return "clubes/formulario"; 
    }

    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        clubServicio.guardar(club);
        return "redirect:/clubes"; 
    }

    @GetMapping("/editar/{id}")
    public String editarClubForm(@PathVariable Long id, Model model) { 
        Club club = clubServicio.obtenerPorId(id);
        if (club != null) {
            model.addAttribute("club", club);
            cargarListasRelacionadas(model);
            return "clubes/formulario"; 
        }
        return "redirect:/clubes";  
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) { 
        clubServicio.eliminar(id);
        return "redirect:/clubes";  
    }

    private void cargarListasRelacionadas(Model model) {
        model.addAttribute("entrenadores", entrenadorServicio.findAll());
        model.addAttribute("asociaciones", asociacionServicio.listarTodas());
        model.addAttribute("competiciones", competicionServicio.listarTodas());
    }
}

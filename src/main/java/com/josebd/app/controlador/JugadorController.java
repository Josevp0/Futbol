package com.josebd.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.josebd.app.modelo.Jugador;
import com.josebd.app.servicio.JugadorService;
import com.josebd.app.servicio.ClubServicio;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorServicio;

    @Autowired
    private ClubServicio clubServicio;


    @GetMapping
    public String listaJugadores(Model model) {
        model.addAttribute("jugadores", jugadorServicio.obtenerTodos());
        return "jugadores/lista"; 
    }

    @GetMapping("/nuevo")
    public String nuevoJugadorForm(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubServicio.obtenerTodos());  
        return "jugadores/formulario";  
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorServicio.guardar(jugador);
        return "redirect:/jugadores"; 
    }

    @GetMapping("/editar/{id}")
    public String editarJugadorForm(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorServicio.obtenerPorId(id);
        if (jugador != null) {
            model.addAttribute("jugador", jugador);
            model.addAttribute("clubes", clubServicio.obtenerTodos()); 
            return "jugadores/formulario"; 
        }
        return "redirect:/jugadores"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorServicio.eliminar(id);
        return "redirect:/jugadores";  
    }
}

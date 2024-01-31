package org.iesvdm.pruebaud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.iesvdm.pruebaud3.service.CategoriaService;
import org.iesvdm.pruebaud3.service.IdiomaService;
import org.iesvdm.pruebaud3.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Controller
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private IdiomaService idiomaService;
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/peliculas")
    public String listar(Model model){
        List<Pelicula> listaPeliculas =  peliculaService.listAll();
        model.addAttribute("listaPeliculas", listaPeliculas);

        BigDecimal horror=listaPeliculas.stream().filter(pelicula -> pelicula.getCategoria().getNombre().equals("Horror")).map(Pelicula::getReplacement_cost).reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2)).orElse(null);
        model.addAttribute("totHorror", horror);
        model.addAttribute("map", listaPeliculas.stream().collect(groupingBy(pelicula -> pelicula.getCategoria().getNombre(),counting())));
        return "peliculas";
    }
    /**
     * va a la pagina para crea un pelicula con un pelicula vacio
     * @param model
     * @return
     */
    @GetMapping("/peliculas/crear")
    public String crear(Model model) {
        //dice la accion porque formPelicula es sea para crear que para editar
        model.addAttribute("action", "crear");
        //paso un pelicula vacio
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("idiomas",idiomaService.listAll());
        model.addAttribute("categorias",categoriaService.listAll());

        //va al formulario de pelicula
        return "formPelicula";
    }
    /**
     * Comprueba los errores y crea en la base de datos y vuelve al index
     * @param model
     * @param p
     * @param bindingResult
     * @return
     */
    @PostMapping("/peliculas/crear")
    public String submitCrear(Model model, @Valid @ModelAttribute Pelicula p, BindingResult bindingResult) {
        //comprueba si hay errores
        if (bindingResult.hasErrors()){
            //vuelve a hacer crear.
            model.addAttribute("action", "crear");
            model.addAttribute("idiomas",idiomaService.listAll());
            model.addAttribute("categorias",categoriaService.listAll());
            model.addAttribute("pelicula", p);
            return "formPelicula";
        }
        peliculaService.create(p);
        return "redirect:/peliculas";
    }
}

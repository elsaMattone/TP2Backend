/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import monprojet.dao.*;
import monprojet.entity.*;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping(path="/")
/**
 *
 * @author 33686
 */
public class CategorieController {
    
    @Autowired
    private CityRepository dao;
    
    @GetMapping(path = "show")
    public String afficheToutesLesCategories(Model model) {
	model.addAttribute("categories", dao.findAll());
	return "showCategories";
    }
    
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("city") City city) {
	return "formulaireCity";
    }
    
    @PostMapping(path = "save")
    public String ajouteLaCategoriePuisMontreLaListe(City city) {
	// cf. https://www.baeldung.com/spring-data-crud-repository-save
	dao.save(city); // Ici on peut avoir une erreur (doublon dans un libellé par exemple)
	return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste	
    }
    
}

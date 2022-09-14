package jana60.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.Ingredient;
import jana60.model.Recipe;
import jana60.repository.IngredientRepository;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	public IngredientRepository ingredientRepo;
	
	
	//metodo che crea nuovo ingrediente
	@GetMapping
	public String newIngredient(Model model) {
		
		//per la form di creazione instanzio new Ingrediente
		model.addAttribute("newIngredient", new Ingredient());
		model.addAttribute("ingredients", ingredientRepo.findAllByOrderByName());
		return"/ingredients";
		
	}
	
	
	
	//metodo che salva nella pagina home la nuova ricetta inserita
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("NewIngredient") Ingredient formSub,BindingResult br) {
	   
		 if(br.hasErrors()) {
			 
			 return "ingredients";
			 
		 }
		 
		 ingredientRepo.save(formSub);
		 
	     return "redirect:/ingredients";
	     
	}
	
	//metodo per cancellare ingr
	@GetMapping("/delete/{id}")
	  public String deleteIngredients(@Valid @PathVariable("id") Integer ingredientId, RedirectAttributes ra) {
		Optional<Ingredient> selezionaId = ingredientRepo.findById(ingredientId);

        if(selezionaId.isPresent()) {

            ingredientRepo.deleteById(ingredientId);
            ra.addFlashAttribute("successMessage", "L'ingrediente " + selezionaId.get().getName() + " è stata eliminata.");

            return "redirect:/ingredients";

        } else 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente che stai provando ad eliminare non esiste");

    }
	  
}



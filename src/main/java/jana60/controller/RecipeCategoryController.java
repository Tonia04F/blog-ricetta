package jana60.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.RecipeCategory;
import jana60.repository.RecipeCategoryRepository;

@Controller
@RequestMapping("/category")
public class RecipeCategoryController {
	
			@Autowired
			public RecipeCategoryRepository categoryRepo;
	
	  
			//metodo che richiama le categorie
			@GetMapping
			public String newCategory(Model model) {
				model.addAttribute("categories", categoryRepo.findAll());
				//nuova categoria newCategory
				model.addAttribute("newCategory",new RecipeCategory());
				return "category";
			}
			
			//creo post mapping per salvare nuova categoria 
			@PostMapping("/add")
			public String saveCategory(@Valid @ModelAttribute("newCategory") RecipeCategory formSub,BindingResult brC) {
			   
				 if(brC.hasErrors()) {
					 
					 return "add";
					 
				 }
				 
				 categoryRepo.save(formSub);
				 
			     return "redirect:/category";
			     
			}
			
}

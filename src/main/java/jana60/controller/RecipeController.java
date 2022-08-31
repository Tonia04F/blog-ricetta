package jana60.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jana60.model.Recipe;
import jana60.repository.RecipeRepository;

@Controller
@RequestMapping("/")
public class RecipeController {

	@Autowired
	public RecipeRepository recipeRepo;
	
	@GetMapping
	public String homePage(Model model) 
	{
		
		List<Recipe> ListSub = (List<Recipe>) recipeRepo.findAll();
		 
		 model.addAttribute("ListSub", ListSub);
		
		return "homePage";
		
	}
	
	@GetMapping("/add")
	public String SubForm(Model model) {
	 model.addAttribute("NewRecipes",new Recipe());
		return "recipesList";

 }
	
	/*@PostMapping("/recipeslist")
	public String RecipeSave(@Valid @ModelAttribute("recipe") Recipe recipeadd, BindingResult br) {
		boolean brError = br.hasErrors();
		boolean checkName = true;
		if (recipeadd.getId() != null) {
			Recipe vecia = recipeRepo.findById(recipeadd.getId()).get();
			if (vecia.getTitle().equals(recipeadd.getTitle())) {
				checkName = false;
			}
		}
		if (checkName && recipeRepo.countByTitleAllIgnoreCase(recipeadd.getTitle()) > 0) {
			br.addError(new FieldError("recipe", "title", "Il nome deve essere unico"));
			brError = true;
		}

		if (brError) {
			return "returnList";
		} else {
			recipeRepo.save(recipeadd);
			return "redirect:/";
		}
	}*/
	
	
	@PostMapping("/save")

	  public String save(@Valid @ModelAttribute("NewRecipes") Recipe formSub,BindingResult br) {
	   
		 recipeRepo.save(formSub);
	      return "redirect:/recipesList";
	    }
	
	
	
	
	
	
	
}

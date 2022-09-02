package jana60.controller;

import java.util.List;
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

import jana60.model.Recipe;
import jana60.model.RecipeCategory;
import jana60.repository.RecipeCategoryRepository;
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
	
	@GetMapping("/recipeslist")
	public String recipeList(Model model) 
	{
		
		List<Recipe> ListSub = (List<Recipe>) recipeRepo.findAll();
		 
		 model.addAttribute("ListSub", ListSub);
		
		return "recipesList";
		
	}
	
	@GetMapping("/add")
	public String SubForm(Model model) {

	 model.addAttribute("NewRecipe",new Recipe());
		return "add";

	}
	
	//metodo che salva nella pagina home la nuova ricetta inserita
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("NewObject") Recipe formSub,BindingResult br) {
	   
		 if(br.hasErrors()) {
			 
			 return "add";
			 
		 }
		 
		 recipeRepo.save(formSub);
		 
	     return "redirect:/";
	     
	}
	
	//metodo per cancellare ricetta
	@GetMapping("/delete/{id}")
	  public String delete(@PathVariable("id") Integer recipeId) {
		  recipeRepo.deleteById(recipeId);
		  return "redirect:/";
		  
	  }
	
		//metodo che modifica una ricetta 
		@GetMapping("/edit/{id}")
		  public String edit(@PathVariable("id") Integer recipeId, Model model) {
		    Optional<Recipe> result = recipeRepo.findById(recipeId);
		    
		    if (result.isPresent()) {
		     model.addAttribute("NewRecipe", result.get());
		      return "/edit";
		    }else{
		      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	  	          "Recipe con id " + recipeId + " not present");
	  	    }
		  }
		
	 }


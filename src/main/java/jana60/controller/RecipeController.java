package jana60.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Recipe;
import jana60.model.Category;
import jana60.repository.IngredientRepository;
import jana60.repository.CategoryRepository;
import jana60.repository.RecipeRepository;

@Controller
@RequestMapping("/")
public class RecipeController {

	@Autowired
	public RecipeRepository recipeRepo;
	
	@Autowired
	public IngredientRepository ingredientRepo;
	
	@Autowired
	public CategoryRepository categoryRepo;
	
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
	
	//metodo search
	@GetMapping("/search")
	public String search(@RequestParam(name = "queryTitle", required = false)String queryTitle, 
			@RequestParam(name = "description", required = false) String queryDescription, 
			@RequestParam(name="categoryId", required = false) Integer categoryId, Model model ){
		
			if (queryTitle != null && queryTitle.isEmpty()) {
			      queryTitle = null;
			}
		    if (queryDescription != null && queryDescription.isEmpty()) {
		    	queryDescription = null;
		    }
		//	@RequestParam(name = "category", required = false)Integer categoryId, l) {
	
		Optional <Category> category=categoryRepo.findById(categoryId);
		List<Recipe> results = new ArrayList<>();

		
		    if (categoryId != null && categoryId==0) {
		    	categoryId = null;
		    }
		
		if(category.isPresent()) {
			List<Recipe> findCategory = recipeRepo.findByCategory(category.get());
			
			results.addAll(findCategory);
		}
		List<Recipe> ListSub = recipeRepo.findByTitleContainingOrDescriptionContaining(queryTitle, queryDescription);
		
		model.addAttribute("results", results);
		model.addAttribute("description", queryDescription);
		model.addAttribute("category", category);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("ListSub", ListSub);
		return "search";
		
	}
		//advanced search
	 @GetMapping("/advancedSearch")
	  public String advancedSearch() {
	    return "advancedSearch";
	  }
			
	
	@GetMapping("/add")
	public String SubForm(Model model) {
		
		model.addAttribute("NewRecipe",new Recipe());
		model.addAttribute("ingredientsList", ingredientRepo.findAll());
		model.addAttribute("categoryList", categoryRepo.findAll());	
	
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
		    	model.addAttribute("ingredientsList", ingredientRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());	
		    	return "/edit";
		    	
		    }else{
		    	
		    	throw new ResponseStatusException(HttpStatus.NOT_FOUND,
		    	"Recipe con id " + recipeId + " not present");
		      
	  	    }
		    
		  }
		
	 }


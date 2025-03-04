package jana60.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
import jana60.model.Comment;
import jana60.repository.IngredientRepository;
import jana60.repository.CategoryRepository;
import jana60.repository.CommentRepository;
import jana60.repository.ImageRepository;
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
	
	@Autowired
	public ImageRepository imageRepo;
	
	@Autowired
	public CommentRepository commentRepo;
	
	//ADMIN- USER
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
    public String admin(Model model) {
        List<Recipe> ListSub = (List<Recipe>) recipeRepo.findAll();
        model.addAttribute("ListSub", ListSub);


        List<Recipe> List7gg = new ArrayList<>();
        for(Recipe r : ListSub) {
            if(r.getPublicationDate().isAfter(LocalDate.of (2022, 9, 9))) {
                List7gg.add(r);
            }
        }
       model.addAttribute("List7gg", List7gg);
        
       List<Recipe> ListMostViewed = recipeRepo.findAllByOrderByCounterViewsDesc();
       model.addAttribute("ListMostViewed", ListMostViewed);
       
       List<Recipe> ListMostCommented = recipeRepo.findAllByOrderByComments();
       model.addAttribute("ListMostCommented", ListMostCommented);

        return "admin";
    }
	
	@GetMapping
	public String homePage(Model model) 
	{
		
		List<Recipe> ListSub = (List<Recipe>) recipeRepo.findAll();
		 
		model.addAttribute("ListSub", ListSub);
		
		List<Recipe> List7gg = new ArrayList<>();
	    for(Recipe r : ListSub) {
	    	if(r.getPublicationDate().isAfter(LocalDate.of (2022, 9, 9))) {
	        	List7gg.add(r);
	    	}
	    }
	    model.addAttribute("List7gg", List7gg);
	    
	    List<Recipe> ListMostViewed = recipeRepo.findAllByOrderByCounterViewsDesc();
	    model.addAttribute("ListMostViewed", ListMostViewed);
		
		return "homePage";
		
	}
	
	@GetMapping("/recipeslist")
	public String recipeList(Model model) 
	{
		
		List<Recipe> ListSub = (List<Recipe>) recipeRepo.findAll();
		 
		model.addAttribute("ListSub", ListSub);
		
		return "recipesList";
		
	}
	
	/*//metodo search
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
		    
		  List<Recipe> ListSub = new ArrayList<>();
		Optional <Category> category=categoryRepo.findById(categoryId);
		

		
		if(category.isPresent()) {
			List<Recipe> results = recipeRepo.findByTitleContainingAndDescriptionContainingOrCategory(queryTitle, queryDescription, category.get());
			ListSub.addAll(results);
		}else {
			ListSub = recipeRepo.findByTitleContainingAndDescriptionContainingOrCategory(queryTitle, queryDescription, null);
		}
		
		model.addAttribute("queryDescription", queryDescription);
		model.addAttribute("category", category);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("ListSub", ListSub);
		return "search";
		
	}
	
	//advanced search
	@GetMapping("/advancedSearch")
	public String advancedSearch() {
		 
			
		 return "advancedSearch";
	}*/
	
	@GetMapping("/advancedSearch")
	public String advancedSearch() {
		return"/search";
	}
	
	//search 2
	@GetMapping("/search")
	public String search(@RequestParam(name="queryTitle")String queryTitle,
						@RequestParam(name="queryDescription")String queryDescription,
						Model model) {
		
		if(queryTitle != null && queryTitle.isEmpty()) {
			queryTitle = null;
		}
		if(queryDescription!= null && queryDescription.isEmpty()) {
			queryDescription = null;
			
		}
		
		List<Recipe>ListSub = recipeRepo.findByTitleContainingOrDescriptionContaining(queryTitle, queryDescription);
		model.addAttribute("ListSub",ListSub);
		return"search"; //modificato ora
	}

	@GetMapping("/search/category/{categoryId}")
	public String searchByCategory(@PathVariable(name="categoryId") Integer categoryId, Model model) {
		
		 List<Recipe> ListSub = new ArrayList<>();
		Optional <Category> category=categoryRepo.findById(categoryId);
		
		if(category.isPresent()) {
			
			List<Recipe> results = recipeRepo.findByCategory(category.get());
			ListSub.addAll(results);
			
		}else 
			ListSub = recipeRepo.findByCategory(null);
				
		model.addAttribute("ListSub", ListSub);
		
		return "search"; // modificato ora
		
	}
	
	@GetMapping("/search/isVegan")
	public String searchVegan(Model model) {
		
		List<Recipe> ListSub = new ArrayList<>();
		
		List<Recipe> results = recipeRepo.findByIsVegan(true);
		ListSub.addAll(results);
		
				
		model.addAttribute("ListSub", ListSub);
		
		return "search";
		
	}
	
	@GetMapping("/search/isVegetarian")
	public String searchVegetarian(Model model) {
		
		List<Recipe> ListSub = new ArrayList<>();
		
		List<Recipe> results = recipeRepo.findByIsVegetarian(true);
		ListSub.addAll(results);
		
				
		model.addAttribute("ListSub", ListSub);
		
		return "search";
		
	}
	
	/*@GetMapping("/advancedSearch")
	public String searchByCategory(@RequestParam(name="queryCategory")Integer categoryId, Model model) {
		
		return "homePage";
	}*/
	
	@GetMapping("/add")
	public String SubForm(Model model) {
		
		model.addAttribute("NewRecipe",new Recipe());
		
		model.addAttribute("ingredientsList", ingredientRepo.findAllByOrderByName());
		model.addAttribute("categoryList", categoryRepo.findAll());	
	
		return "add";

	}
	
	//metodo che salva nella pagina home la nuova ricetta inserita
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("NewObject") Recipe formSub,BindingResult br) {
	   
		 if(br.hasErrors()) {
			 
			 return "add";
			 
		 }
		 
		 formSub.isVegan();
		 formSub.isVegetarian();
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
		    	model.addAttribute("ingredientsList", ingredientRepo.findAllByOrderByName());
				model.addAttribute("categoryList", categoryRepo.findAll());	
		    	return "/edit";
		    	
		    }else{
		    	
		    	throw new ResponseStatusException(HttpStatus.NOT_FOUND,
		    	"La ricetta che cerchi non è presente");
		      
	  	    }
		    
		}
		
		//pagina di dettaglio ricette 
        @GetMapping("/recipeDetails/{recipeId}")
        public String recipeDetails(@PathVariable(name="recipeId") Integer recipePrimaryKey, Model model ) {
        	
            Recipe currentRecipe = recipeRepo.findById(recipePrimaryKey).get();
            
            currentRecipe.incrementViews();
            recipeRepo.save(currentRecipe);
            
            List<Comment> listComment = (List<Comment>) commentRepo.findAll();

            model.addAttribute("listComment", listComment);
            model.addAttribute("recipe", currentRecipe);
            model.addAttribute("newComment", new Comment());

            return "recipeDetails";

        }
		
}
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
	 model.addAttribute("NuovoOggetto",new Recipe());
		return "/add";

 }
	
	
	@PostMapping("/save")
	  public String save(@Valid @ModelAttribute("NuovoOggetto") Recipe formSub,BindingResult br) {
	   
		 if(br.hasErrors()) {
			 return "/add";
		 }
		 
		 recipeRepo.save(formSub);
	      return "redirect:homePage";
	    }
	
	
	
	
	
	
	
}

package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.repository.RecipeRepository;

@Controller
@RequestMapping("/")
public class RecipeController {

	@Autowired
	public RecipeRepository recipeRepo;
	
	@GetMapping
	public String homePage() {
		
		return "homePage";
		
	}
	
}

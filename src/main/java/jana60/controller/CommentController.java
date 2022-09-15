package jana60.controller;

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

import jana60.model.Comment;
import jana60.model.Recipe;
import jana60.repository.CommentRepository;
import jana60.repository.RecipeRepository;

@Controller
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	public CommentRepository commentRepo;

	@Autowired
	public RecipeRepository recipeRepo;
	
	//metodo che crea nuovo commento
	@GetMapping
	public String newComment(Model model) {
		
		model.addAttribute("comments", commentRepo.findAll());
		//per la form di creazione instanzio new Ingrediente
		model.addAttribute("newComment", new Comment());
		return"/recipeDetails";
		
	}
		
	//metodo che salva nella pagina home la nuova ricetta inserita
	@PostMapping("/save/{recipeId}")
	public String save(@Valid  @PathVariable(name="recipeId")Integer recipeId, @ModelAttribute("newComment") Comment formSub,BindingResult br) {
		   
		formSub.setRecipes(recipeRepo.findById(recipeId).get());
				
		if(br.hasErrors()) {
				 
			return "comments";
				 
		}
			 
		commentRepo.save(formSub);	
		
		Recipe currentRecipe = recipeRepo.findById(recipeId).get();
		currentRecipe.addComment(formSub);
		recipeRepo.save(currentRecipe);
		
		return "redirect:/recipeDetails/" + recipeId;
		     
	}
	
	//metodo per cancellare ingr
		@GetMapping("/delete/{recipeId}")
		  public String deleteIngredients(@Valid @PathVariable("id") Integer commentId, RedirectAttributes ra) {
			Optional<Comment> selezionaId = commentRepo.findById(commentId);

	        if(selezionaId.isPresent()) {

	            commentRepo.deleteById(commentId);
	            ra.addFlashAttribute("successMessage", "il commento" + selezionaId.get().getName() + " è stata eliminata.");

	            return "redirect:/recipeDetails";

	        } else 
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente che stai provando ad eliminare non esiste");

	    }
	
		
}
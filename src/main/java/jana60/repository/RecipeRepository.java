package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Category;
import jana60.model.Recipe;

public interface RecipeRepository  extends CrudRepository<Recipe, Integer> {

	//metodo corrispondente al select like del db per effettuare la ricerca nel search
	public List<Recipe> findByTitleContainingOrDescriptionContaining(String queryTitle, String description);
	
	public List<Recipe> findByCategory(Category categoryId);
}

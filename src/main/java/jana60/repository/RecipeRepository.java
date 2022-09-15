package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Category;
import jana60.model.Recipe;

public interface RecipeRepository  extends CrudRepository<Recipe, Integer> {

	public List<Recipe> findByTitleContainingOrDescriptionContaining(String queryTitle, String queryDescription);
	public List<Recipe> findByCategory(Category categoryId);
	public List<Recipe> findByIsVegan(Boolean isVegan);
	public List<Recipe> findByIsVegetarian(Boolean isVegetarian);
	
	//public List<Recipe> findAllOrderByCounterViews();
	
}

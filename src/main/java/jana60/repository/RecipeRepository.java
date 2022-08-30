package jana60.repository;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Recipe;

public interface RecipeRepository  extends CrudRepository<Recipe, Integer> {

}

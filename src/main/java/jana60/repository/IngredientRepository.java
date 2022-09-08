package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
	List<Ingredient> findAllByOrderByName();
}

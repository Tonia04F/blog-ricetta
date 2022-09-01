package jana60.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Ingredient {
	
	//ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Il nome è necessario:")
	@Column(nullable = false)
	private String name;
	
	private boolean isVegan;
	
	private boolean isVegetarian;
	
	@OneToMany(mappedBy = "currentIngredient")
	private List<IngredientAmount> currentRecipe;

	//GETTERS AND SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegan() {
		return isVegan;
	}

	public void setVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public List<IngredientAmount> getCurrentRecipe() {
		return currentRecipe;
	}

	public void setCurrentRecipe(List<IngredientAmount> currentRecipe) {
		this.currentRecipe = currentRecipe;
	}
	
}

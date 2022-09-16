package jana60.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.validation.constraints.NotEmpty;

@Entity
public class Ingredient {
	
	//ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Il nome è necessario")
	@Column(nullable = false)
	private String name;
	
	private Boolean isVegan;
	
	private Boolean isVegetarian;
	
	@NotEmpty(message = "La quantità è necessaria")
	@Column(nullable = false)
	private String ingredientAmount;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Recipe> recipes;

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

	public Boolean getIsVegan() {
		return isVegan;
	}

	public void setIsVegan(Boolean isVegan) {
		this.isVegan = isVegan;
	}

	public Boolean getIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public String getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(String ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}
	
	

	
	
}

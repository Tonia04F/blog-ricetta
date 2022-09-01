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
	
	@NotEmpty(message = "Il nome è necessario:")
	@Column(nullable = false)
	private String name;
	
	private boolean isVegan;
	
	private boolean isVegetarian;
	
	private String amount;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Recipe> recipes;

	//GETTERS AND SETTERS
	
	
	public Integer getId() {
		return id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	
	
}

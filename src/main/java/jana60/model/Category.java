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
public class Category {
	
	//ATTRIBUTI 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Il nome della categoria è necessario")
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Recipe> recipeCategory;
	

	public List<Recipe> getRecipeCategory() {
		return recipeCategory;
	}

	public void setRecipeCategory(List<Recipe> recipeCategory) {
		this.recipeCategory = recipeCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}
	
	
	

}

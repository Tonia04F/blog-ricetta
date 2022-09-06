package jana60.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty(message="È necessario inserire una categoria")
	private String name;
	
	@OneToMany(mappedBy= "categoria")
	private List<Recipe> recipe;
	//test modifica classe

	
	public Integer getId() {
		return id;
	}

	public List<Recipe> getRecipe() {
		return recipe;
	}

	public void setRicetta(List<Recipe> recipe) {
		this.recipe = recipe;
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

}

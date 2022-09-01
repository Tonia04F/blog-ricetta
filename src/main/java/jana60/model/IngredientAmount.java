package jana60.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IngredientAmount {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe currentRecipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient currentIngredient;
    
    private String amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Recipe getCurrentRecipe() {
		return currentRecipe;
	}

	public void setCurrentRecipe(Recipe currentRecipe) {
		this.currentRecipe = currentRecipe;
	}

	public Ingredient getCurrentIngredient() {
		return currentIngredient;
	}

	public void setCurrentIngredient(Ingredient currentIngredient) {
		this.currentIngredient = currentIngredient;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}

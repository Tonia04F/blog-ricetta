package jana60.model;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Recipe {
	
	//ATTRIBUTES

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Il titolo della ricetta è necessario")
	@Column(nullable = false)
	private String title;
	
	private Boolean isVegan;
	
	private Integer preparationTime;
	
	private Integer difficulty;
	
	@Lob
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publicationDate;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	@ManyToOne
	public Category category;
	
	@ManyToMany
	private List<Comment> comments;
	
	@OneToMany
	private List<Image> images;

	//GETTERS AND SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPublicationDate() {
		
		//formatDate();
		return publicationDate;
		
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Image> getImage() {
		return images;
	}

	public void setImage(List<Image> images) {
		this.images = images;
	}


	
	
	//METHODS OF THE CLASS
	
	/*public String formatDate() {
		
		String formattedDate;
		DateTimeFormatter formdate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formattedDate = this.publicationDate.format(formdate);
		
		return formattedDate;*/
		
	/*public void isVegan() {
		
		for (Ingredient ing : ingredients) {
			
			if(ing.isVegan())
				isVegan = true;
			else
				isVegan = false;
			
		}
		
	}*/
	
}

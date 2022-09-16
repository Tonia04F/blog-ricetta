package jana60.model;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	
	private Boolean isVegetarian;
	
	private Integer counterViews = 0;
	
	@NotEmpty(message = "Il tempo di preparazione della ricetta è necessario")
	@Column(nullable = false)
	private Integer preparationTime;
	
	@NotEmpty(message = "La difficoltà della ricetta è necessaria")
	@Column(nullable = false)
	@Min(value=1)
	@Max(value=3)
	private Integer difficulty;
	
	@NotEmpty(message = "La preparazione della ricetta è necessaria")
	@Column(nullable = false)
	@Lob
	private String description;
	
	@NotEmpty(message = "La data di pubblicazione della ricetta è necessaria")
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publicationDate;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	@ManyToOne
	public Category category;
	
	@OneToMany(mappedBy = "recipes")
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
	
	public Integer getCounterViews() {
		return counterViews;
	}

	public void setCounterViews(Integer counterViews) {
		this.counterViews = counterViews;
	}
	public String getTitle() {
		return title;
	}

	public Boolean getIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
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

	public Boolean getIsVegan() {
		return isVegan;
	}

	public void setIsVegan(Boolean isVegan) {
		this.isVegan = isVegan;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	//METHODS OF THE CLASS
			
	public void isVegan() {
		
		for (Ingredient ing : ingredients) {
			
			if(ing.getIsVegan())

				this.isVegan = true;
			else {
				
				this.isVegan = false;
				break;
				
			}

		}
	
	}
	
	public void isVegetarian() {
	
		for (Ingredient ing : ingredients) {
			
			if(ing.getIsVegetarian())

				this.isVegetarian = true;
			else {
				
				this.isVegetarian = false;
				break;
				
			}

		}
	
	}
	
	public void incrementViews() {
		
		counterViews++;
		
	}
	//METODO PER AGGIUNGERE ED ELIMINARE IL COMMENTO ALLA LISTA DALLA RICETTA (Precedente al mappedBy che collegava le entità)
	/*public void addComment(Comment comment) {
		
		this.comments.add(comment);
		
	}
	
	public void findAndDeleteComment(Optional<Comment> currentComment) {
		
		for(Comment c : comments) {
			
			if(c.getId() == currentComment.get().getId()) {
				
				comments.remove(currentComment);
				
			}
						
		}
				
	}*/
	
}

package jana60.model;


import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Comment {

	
	
	//ATTRIBUTES
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		
		@NotEmpty(message = "Il nome è necessario:")
		@Column(nullable = false)
		private String name;
		
		@NotEmpty(message = "I'email è necessario:")
		@Column(nullable = false)
		private String email;
		
		@ManyToOne
		private Recipe recipes;
		
		private String comment;
		
		//GETTER E SETTER
		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}

		public Recipe getRecipes() {
			return recipes;
		}


		public void setRecipes(Recipe recipes) {
			this.recipes = recipes;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getComment() {
			return comment;
		}


		public void setComment(String comment) {
			this.comment = comment;
		}

		//Metodi della classe
		
		public void deleteComment (Optional<Comment> comment) {
			
			comment.get().setRecipes(null);
			
		}
		
}

package jana60.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

	
	private Integer id;
	
	private Recipe recipe;
	
	private MultipartFile contentMultipart;

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}

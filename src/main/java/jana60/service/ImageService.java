package jana60.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Image;
import jana60.model.ImageForm;

import jana60.model.Recipe;
import jana60.repository.ImageRepository;
import jana60.repository.RecipeRepository;

@Service
public class ImageService {

	
	@Autowired
	private ImageRepository imageRepo;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	
	//Creiamo lista di immagini per ricetta con ID specifico
		public List<Image> getImageByRecipeId(Integer recipeId)
		{
			
			Recipe recipe = recipeRepo.findById(recipeId).get();
			return imageRepo.findByRecipe(recipe);
			
		}
		
		public ImageForm createImageForm(Integer recipeId)
		{
			Recipe recipe = recipeRepo.findById(recipeId).get();
			ImageForm img = new ImageForm();
			img.setRecipe(recipe);
			return img;
		}
	
		//Serializzo l'immagine
		public Image createImage(ImageForm imageForm) throws IOException {
			
			Image imgToSave = new Image();
			imgToSave.setRecipe(imageForm.getRecipe());
			
			if (imageForm.getContentMultipart() != null)
			{
				byte[] contentSerialized = imageForm.getContentMultipart().getBytes();
				imgToSave.setContent(contentSerialized);				
			}	
			//Salvo immagine serializzata su db
			return imageRepo.save(imgToSave);	
		}
		
		public byte[] getImageContent(Integer id) 
		{
			
			Image img = imageRepo.findById(id).get();
			return img.getContent();
			
			
		}
		
		
}

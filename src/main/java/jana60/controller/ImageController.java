package jana60.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Image;
import jana60.model.ImageForm;

import jana60.service.ImageService;


@Controller
@RequestMapping("/image")
public class ImageController
{

	@Autowired
	private ImageService service;
	
	@GetMapping("/{recipeId}")
	public String recipeImage(@PathVariable("recipeId") Integer recipeId, Model model)
	{
		List<Image> images = service.getImageByRecipeId(recipeId);
		ImageForm imageForm = service.createImageForm(recipeId);
		
		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);
		return "/List";
		
	
	}
	
	 @PostMapping("/salva")
	 public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) 
	 {
		 try {
		      Image savedImage = service.createImage(imageForm);
		      return "redirect:/image/" + savedImage.getRecipe().getId();
		 } catch (IOException e) {
		      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		    }
	 }
	 
	 /*
	   * Controller che in base all'id dell'Image restituisce il contenuto
	   */
	 
	 @RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	 public ResponseEntity<byte[]> getImageContent(@PathVariable("imageId") Integer imageId) {
		// recupero il content dal database
		    byte[] content = service.getImageContent(imageId);
		    // preparo gli headers della response con il tipo di contenuto
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_JPEG);
		    // ritorno il contenuto, gli headers e lo status http
		    return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	 }
	 
}
